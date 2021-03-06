package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
)

func main() {
	// numbers := []int{
	// 	1721,
	// 	979,
	// 	366,
	// 	299,
	// 	675,
	// 	1456,
	// }
	file, _ := os.Open("input01.txt")
	numbers := ToIntSlice(file)
	sort.Ints(numbers)

	for i, n := range numbers {
		rest := numbers[i+1:]
		wanted := 2020 - n
		pos := sort.SearchInts(rest, wanted)

		if pos != len(rest) && rest[pos] == wanted {
			solution := n * wanted
			fmt.Printf("Found %v + %v = 2020\n", n, wanted)
			fmt.Printf("%v * %v = %v\n", n, wanted, solution)
			break
		}
	}

	for i, n := range numbers {
		for j, m := range numbers[i+1:] {
			wanted := 2020 - n - m

			if wanted < 0 {
				break
			}

			rest := numbers[i+j+2:]
			pos := sort.SearchInts(rest, wanted)

			if pos != len(rest) && rest[pos] == wanted {
				solution := n * m * wanted
				fmt.Printf("Found %v + %v + %v = 2020\n", n, m, wanted)
				fmt.Printf("%v * %v * %v = %v\n", n, m, wanted, solution)
				break
			}
		}

	}
}

// ToIntSlice lines -> ints
func ToIntSlice(input *os.File) []int {
	scanner := bufio.NewScanner(input)
	numbers := make([]int, 0, 1000)

	for scanner.Scan() {
		line := scanner.Text()
		n, err := strconv.Atoi(line)

		if err != nil {
			panic(fmt.Sprintf("Unable to convert %v to integer", line))
		}

		numbers = append(numbers, n)
	}

	return numbers
}
