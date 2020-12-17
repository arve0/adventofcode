package main

import (
	"bytes"
	"fmt"
	"io/ioutil"
)

func main() {
	testForrest, testWidth := parse(testInput())
	fmt.Println(solve(testForrest, testWidth, coordinate{3, 1}))

	input, _ := ioutil.ReadFile("input03.txt")
	forrest, width := parse(input)
	fmt.Println(solve(forrest, width, coordinate{3, 1}))

	options := []coordinate{
		{1, 1},
		{3, 1},
		{5, 1},
		{7, 1},
		{1, 2},
	}
	n := 1
	m := 1
	for _, option := range options {
		m *= solve(testForrest, testWidth, option)
		n *= solve(forrest, width, option)
	}
	fmt.Println(m)
	fmt.Println(n)
}

func parse(input []byte) ([]int, int) {
	lines := bytes.Split(input, []byte("\n"))
	lineWidth := len(lines[0])
	forrest := make([]int, len(lines))

	for i, line := range lines {
		n := 0
		for j, ch := range line {
			if ch == '#' {
				n |= 1 << j
			}
		}

		forrest[i] = n
	}

	return forrest, lineWidth
}

func solve(forrest []int, lineWidth int, velocity coordinate) int {
	pos := coordinate{}
	hit := 0

	for int(pos.y+velocity.y) < len(forrest) {
		pos.x += velocity.x
		pos.x %= lineWidth
		pos.y += velocity.y
		bitPos := 1 << pos.x

		if bitPos&forrest[pos.y] != 0 {
			hit++
			// fmt.Println("hit", pos, forrest[pos.y], bitPos&forrest[pos.y])
		} else {
			// fmt.Println("miss", pos, forrest[pos.y], bitPos&forrest[pos.y])
		}
	}

	return hit
}

type coordinate struct {
	x int
	y int
}

func testInput() []byte {
	return []byte(`..##.......
#...#...#..
.#....#..#.
..#.#...#.#
.#...##..#.
..#.##.....
.#.#.#....#
.#........#
#.##...#...
#...##....#
.#..#...#.#`)
}
