package main

import (
	"fmt"
	"io/ioutil"
	"regexp"
	"strconv"
	"strings"
)

func main() {
	testInput := parse(`1-3 a: abcde
1-3 b: cdefg
2-9 c: ccccccccc`)
	testOk := 0
	for _, p := range testInput {
		if ok(p) {
			testOk++
		}
	}
	fmt.Printf("Test ok: %v\n", testOk)

	inputBytes, _ := ioutil.ReadFile("input02.txt")
	input := parse(string(inputBytes))
	testOk = 0
	for _, p := range input {
		if ok(p) {
			testOk++
		}
	}
	fmt.Printf("Ok: %v\n", testOk)
}

type passwordWithPolicy struct {
	min      int
	max      int
	ch       byte
	password string
}

func parse(input string) []passwordWithPolicy {
	lines := strings.Split(strings.TrimSpace(input), "\n")
	passwords := make([]passwordWithPolicy, 0)
	pattern := regexp.MustCompile("^([0-9]+)-([0-9]+) ([a-z]): ([a-z]+)$")

	for i, line := range lines {
		match := pattern.FindAllStringSubmatch(line, -1)[0]

		if len(match) != 5 {
			panic(fmt.Sprintf("Unable to parse line %v '%v' got %v", i, line, match))
		}

		passwords = append(passwords, passwordWithPolicy{
			min:      mustAtoi(match[1]),
			max:      mustAtoi(match[2]),
			ch:       match[3][0],
			password: match[4],
		})
	}

	return passwords
}

func ok(p passwordWithPolicy) bool {
	count := 0

	for i := 0; i < len(p.password); i++ {
		if p.password[i] == p.ch {
			count++
		}
	}

	return count >= p.min && count <= p.max
}

func mustAtoi(s string) int {
	n, err := strconv.Atoi(s)

	if err != nil {
		panic(err)
	}

	return n
}
