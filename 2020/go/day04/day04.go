package main

import (
	"fmt"
	"io/ioutil"
	"strconv"
	"strings"
)

func main() {
	passports := parse(testInput())
	n, m := 0, 0
	for _, passport := range passports {
		if hasValidPassportKeys(passport) {
			n++
		}
		if isValidPassport(passport) {
			m++
		}
	}
	fmt.Println(n, m)

	input, _ := ioutil.ReadFile("input04.txt")
	passports = parse(string(input))
	n, m = 0, 0
	for _, passport := range passports {
		if hasValidPassportKeys(passport) {
			n++
		}
		if isValidPassport(passport) {
			m++
		}
	}
	// 169 too high
	fmt.Println(n, m)
}

type passport struct {
	properties map[string]string
}

type validPassport func(p passport) bool

func parse(input string) []passport {
	sections := strings.Split(strings.TrimSpace(input), "\n\n")
	passports := make([]passport, len(sections))

	for i, section := range sections {
		properties := strings.Split(strings.Replace(section, "\n", " ", -1), " ")
		props := make(map[string]string, len(properties))

		for _, prop := range properties {
			keyValue := strings.Split(prop, ":")
			props[keyValue[0]] = keyValue[1]
		}

		passports[i] = passport{props}
	}

	return passports
}

func hasValidPassportKeys(p passport) bool {
	keys := []string{
		"byr", // (Birth Year)
		"iyr", // (Issue Year)
		"eyr", // (Expiration Year)
		"hgt", // (Height)
		"hcl", // (Hair Color)
		"ecl", // (Eye Color)
		"pid", // (Passport ID)
		// "cid", // (Country ID)
	}

	if len(p.properties) < len(keys) {
		return false
	}
	for _, key := range keys {
		if _, has := p.properties[key]; !has {
			return false
		}
	}
	return true
}

func isValidPassport(p passport) bool {
	rules := []validPassport{
		hasValidPassportKeys,
		keyIsFourDigitsAboveAndBelow("byr", "1920", "2002"), // birth year
		keyIsFourDigitsAboveAndBelow("iyr", "2010", "2020"), // issue year
		keyIsFourDigitsAboveAndBelow("eyr", "2020", "2030"), // expiration year
		isValidHeight,
		isValidHairColor,
		isValidEyeColor,
		isValidPassportID,
	}

	for _, rule := range rules {
		if !rule(p) {
			return false
		}
	}

	return true
}

func keyIsFourDigitsAboveAndBelow(key, above, below string) validPassport {
	return func(p passport) bool {
		value := p.properties[key]
		if len(value) != 4 || value < above || value > below {
			return false
		}
		return true
	}
}

func isOneOf(value string, values []string) bool {
	for _, wanted := range values {
		if value == wanted {
			return true
		}
	}
	return false
}

func isAboveAndBelow(value string, above, below int) bool {
	val := atoi(value)
	return val >= above && val <= below
}

func isValidHeight(p passport) bool {
	hgt := p.properties["hgt"]
	if len(hgt) < 2 {
		return false
	}
	hgtUnit := hgt[len(hgt)-2:]
	if !isOneOf(hgtUnit, []string{"cm", "in"}) {
		return false
	}
	hgtValue := hgt[:len(hgt)-2]
	if hgtUnit == "cm" && !isAboveAndBelow(hgtValue, 150, 193) {
		return false
	}
	if hgtUnit == "in" && !isAboveAndBelow(hgtValue, 59, 76) {
		return false
	}
	return true
}

func isValidEyeColor(p passport) bool {
	ecl := p.properties["ecl"]
	wanted := []string{"amb", "blu", "brn", "gry", "grn", "hzl", "oth"}
	if !isOneOf(ecl, wanted) {
		return false
	}
	return true
}

func isValidHairColor(p passport) bool {
	hcl := p.properties["hcl"]
	if hcl[0] != '#' || len(hcl) != 7 {
		return false
	}
	_, hclErr := strconv.ParseUint(hcl[1:], 16, 64)
	if hclErr != nil {
		return false
	}
	return true
}

func isValidPassportID(p passport) bool {
	pid := p.properties["pid"]
	if len(pid) != 9 {
		return false
	}
	_, pidErr := strconv.Atoi(pid)
	if pidErr != nil {
		return false
	}
	return true
}

func testInput() string {
	return `ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm

iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
hcl:#cfa07d byr:1929

hcl:#ae17e1 iyr:2013
eyr:2024
ecl:brn pid:760753108 byr:1931
hgt:179cm

hcl:#cfa07d eyr:2025 pid:166559648
iyr:2011 ecl:brn hgt:59in

eyr:1972 cid:100
hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926

iyr:2019
hcl:#602927 eyr:1967 hgt:170cm
ecl:grn pid:012533040 byr:1946

hcl:dab227 iyr:2012
ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277

hgt:59cm ecl:zzz
eyr:2038 hcl:74454a iyr:2023
pid:3556412378 byr:2007

pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
hcl:#623a2f

eyr:2029 ecl:blu cid:129 byr:1989
iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm

hcl:#888785
hgt:164cm byr:2001 iyr:2015 cid:88
pid:545766238 ecl:hzl
eyr:2022

iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719
`
}

func atoi(s string) int {
	n, err := strconv.Atoi(s)

	if err != nil {
		return 0
	}

	return n
}
