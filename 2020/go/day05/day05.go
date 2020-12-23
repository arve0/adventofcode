package main

import (
	"fmt"
	"io/ioutil"
	"sort"
	"strconv"
	"strings"
)

func main() {
	for _, input := range testInput() {
		s := seatFromText(input)
		fmt.Println(s, s.id())
	}

	input, _ := ioutil.ReadFile("input05.txt")
	lines := strings.Split(strings.TrimSpace(string(input)), "\n")
	ids := make([]int64, len(lines))
	max := seat{}
	for i, line := range lines {
		s := seatFromText(line)
		ids[i] = s.id()
		if max.id() < s.id() {
			max = s
		}
	}
	fmt.Println("part 1", max, max.id())

	sort.Slice(ids, func(i, j int) bool { return ids[i] < ids[j] })
	for i, v := range ids[1:] {
		expectedBelow := v - 1
		if ids[i] != expectedBelow {
			fmt.Println("Part 2", ids[i], v)
		}
	}
}

type seat struct {
	row int64
	col int64
}

func (s seat) id() int64 {
	return s.row*8 + s.col
}

func seatFromText(t string) seat {
	rowDigits := strings.ReplaceAll(t[:7], "B", "1")
	rowDigits = strings.ReplaceAll(rowDigits, "F", "0")
	columnDigits := strings.ReplaceAll(t[7:], "R", "1")
	columnDigits = strings.ReplaceAll(columnDigits, "L", "0")

	row, _ := strconv.ParseInt(rowDigits, 2, 64)
	col, _ := strconv.ParseInt(columnDigits, 2, 64)

	return seat{row, col}
}

func testInput() []string {
	return []string{"FBFBBFFRLR", "BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL"}
}
