extern crate permutohedron;

use permutohedron::Heap;
use std::collections::hash_set::HashSet;

fn main() {
  let happiness = parse_happiness(get_test_input());
  assert_eq!(happiness.len(), 4);
  assert_eq!(solve(&happiness), 330);

  let mut happiness = parse_happiness(get_input());
  solve(&happiness);

  {
    happiness.iter_mut().for_each(|row| row.insert(0, 0));
    let my_happiness: Vec<isize> = (0..happiness.len() + 1).map(|_| 0).collect();
    happiness.insert(0, my_happiness);
  }
  solve(&happiness);
}

fn solve(happiness: &Vec<Vec<isize>>) -> isize {
  let mut persons: Vec<_> = (0..happiness.len()).collect();
  let permutations = Heap::new(&mut persons);

  let mut max = 0;
  for seating in permutations {
    let sum = calc_happiness(&seating, &happiness);
    if sum > max {
      max = sum;
      println!("{:?}: {}", seating, sum);
    }
  }

  max
}

fn parse_happiness(s: &str) -> Vec<Vec<isize>> {
  let persons: HashSet<String> = s.split("\n")
    .flat_map(|line| line.split(" ").next())
    .map(|name| name.to_owned())
    .collect();

  let len = persons.len();
  let mut happiness = Vec::new();
  let mut lines = s.split("\n");

  for i in 0..len {
    for j in 0..len {
      if j == 0 {
        happiness.push(Vec::new());
      }
      if i == j {
        happiness[i].push(0);
        continue;
      }

      let mut line = lines.next().unwrap().split(" ");
      let sign = if line.nth(2).unwrap() == "lose" {
        -1
      } else {
        1
      };
      let weight: isize = line.next().unwrap().parse().unwrap();
      happiness[i].push(sign * weight);
    }
  }

  happiness
}

fn calc_happiness(persons: &Vec<usize>, happiness: &Vec<Vec<isize>>) -> isize {
  let mut sum = 0;
  let len = persons.len();

  for i in 0..len {
    let me = persons[i];
    let prev = persons[safe_i(i, -1, len)];
    let next = persons[safe_i(i, 1, len)];

    sum += happiness[me][prev];
    sum += happiness[me][next];
  }

  sum
}

fn safe_i(i: usize, n: isize, len: usize) -> usize {
  let i = i as isize;
  let len = len as isize;
  ((i + n + len) % len) as usize
}

fn get_test_input() -> &'static str {
  "Alice would gain 54 happiness units by sitting next to Bob.
Alice would lose 79 happiness units by sitting next to Carol.
Alice would lose 2 happiness units by sitting next to David.
Bob would gain 83 happiness units by sitting next to Alice.
Bob would lose 7 happiness units by sitting next to Carol.
Bob would lose 63 happiness units by sitting next to David.
Carol would lose 62 happiness units by sitting next to Alice.
Carol would gain 60 happiness units by sitting next to Bob.
Carol would gain 55 happiness units by sitting next to David.
David would gain 46 happiness units by sitting next to Alice.
David would lose 7 happiness units by sitting next to Bob.
David would gain 41 happiness units by sitting next to Carol."
}

fn get_input() -> &'static str {
  "Alice would gain 2 happiness units by sitting next to Bob.
Alice would gain 26 happiness units by sitting next to Carol.
Alice would lose 82 happiness units by sitting next to David.
Alice would lose 75 happiness units by sitting next to Eric.
Alice would gain 42 happiness units by sitting next to Frank.
Alice would gain 38 happiness units by sitting next to George.
Alice would gain 39 happiness units by sitting next to Mallory.
Bob would gain 40 happiness units by sitting next to Alice.
Bob would lose 61 happiness units by sitting next to Carol.
Bob would lose 15 happiness units by sitting next to David.
Bob would gain 63 happiness units by sitting next to Eric.
Bob would gain 41 happiness units by sitting next to Frank.
Bob would gain 30 happiness units by sitting next to George.
Bob would gain 87 happiness units by sitting next to Mallory.
Carol would lose 35 happiness units by sitting next to Alice.
Carol would lose 99 happiness units by sitting next to Bob.
Carol would lose 51 happiness units by sitting next to David.
Carol would gain 95 happiness units by sitting next to Eric.
Carol would gain 90 happiness units by sitting next to Frank.
Carol would lose 16 happiness units by sitting next to George.
Carol would gain 94 happiness units by sitting next to Mallory.
David would gain 36 happiness units by sitting next to Alice.
David would lose 18 happiness units by sitting next to Bob.
David would lose 65 happiness units by sitting next to Carol.
David would lose 18 happiness units by sitting next to Eric.
David would lose 22 happiness units by sitting next to Frank.
David would gain 2 happiness units by sitting next to George.
David would gain 42 happiness units by sitting next to Mallory.
Eric would lose 65 happiness units by sitting next to Alice.
Eric would gain 24 happiness units by sitting next to Bob.
Eric would gain 100 happiness units by sitting next to Carol.
Eric would gain 51 happiness units by sitting next to David.
Eric would gain 21 happiness units by sitting next to Frank.
Eric would gain 55 happiness units by sitting next to George.
Eric would lose 44 happiness units by sitting next to Mallory.
Frank would lose 48 happiness units by sitting next to Alice.
Frank would gain 91 happiness units by sitting next to Bob.
Frank would gain 8 happiness units by sitting next to Carol.
Frank would lose 66 happiness units by sitting next to David.
Frank would gain 97 happiness units by sitting next to Eric.
Frank would lose 9 happiness units by sitting next to George.
Frank would lose 92 happiness units by sitting next to Mallory.
George would lose 44 happiness units by sitting next to Alice.
George would lose 25 happiness units by sitting next to Bob.
George would gain 17 happiness units by sitting next to Carol.
George would gain 92 happiness units by sitting next to David.
George would lose 92 happiness units by sitting next to Eric.
George would gain 18 happiness units by sitting next to Frank.
George would gain 97 happiness units by sitting next to Mallory.
Mallory would gain 92 happiness units by sitting next to Alice.
Mallory would lose 96 happiness units by sitting next to Bob.
Mallory would lose 51 happiness units by sitting next to Carol.
Mallory would lose 81 happiness units by sitting next to David.
Mallory would gain 31 happiness units by sitting next to Eric.
Mallory would lose 73 happiness units by sitting next to Frank.
Mallory would lose 89 happiness units by sitting next to George."
}
