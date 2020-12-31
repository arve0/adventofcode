package main

import (
	"fmt"
	"io/ioutil"
	"strings"
)

func main() {
	fmt.Println(inputToAnswer(testInput))
	fmt.Println(inputToAnswer(input))

	fmt.Println(inputToAnswerPart2(testInput))
	fmt.Println(inputToAnswerPart2(input))
}

func inputToAnswerPart2(getInput func() string) int {
	answer := 0

	for _, group := range splitToGroups(getInput()) {
		persons := strings.Count(group, "\n") + 1
		personAnswers := groupToAnswersMap(group)

		for _, numberOfPersonAnswers := range personAnswers {
			if persons == numberOfPersonAnswers {
				answer++
			}
		}
	}

	return answer
}

func groupToAnswersMap(group string) map[rune]int {
	answers := make(map[rune]int, 0)

	for _, ch := range group {
		if ch == '\n' {
			continue
		}
		answers[ch]++
	}

	return answers
}

func input() string {
	bytes, _ := ioutil.ReadFile("input06.txt")
	return string(bytes)
}

func inputToAnswer(getInput func() string) int {
	answer := 0

	for _, group := range splitToGroups(getInput()) {
		answers := groupToAnswersSet(group)
		answer += len(answers)
	}

	return answer
}

func groupToAnswersSet(group string) map[rune]bool {
	answers := make(map[rune]bool, 0)

	for _, ch := range group {
		if ch == '\n' {
			continue
		}
		answers[ch] = true
	}

	return answers
}

func splitToGroups(s string) []string {
	return strings.Split(s, "\n\n")
}

func testInput() string {
	return `abc

a
b
c

ab
ac

a
a
a
a

b`
}
