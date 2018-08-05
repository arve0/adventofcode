extern crate permutohedron;

use permutohedron::Heap;
use std::usize;

fn main() {
    let min = solve(parse_distances(get_test_input()));
    println!("{}", min);

    let min = solve(parse_distances(get_input()));
    println!("{:?}", min);

    let max = solve_max(parse_distances(get_input()));
    println!("{:?}", max);
}

fn solve(distances: Vec<Vec<usize>>) -> usize {
    let mut path: Vec<_> = (0..distances[0].len()).collect();
    let permutations = Heap::new(&mut path);

    let mut min = usize::MAX;
    for path in permutations {
        let d = calc_distance(&path, &distances);
        if d < min {
            min = d;
        }
    }

    min
}

fn solve_max(distances: Vec<Vec<usize>>) -> usize {
    let mut path: Vec<_> = (0..distances[0].len()).collect();
    let permutations = Heap::new(&mut path);

    let mut max = usize::MIN;
    for path in permutations {
        let d = calc_distance(&path, &distances);
        if d > max {
            max = d;
        }
    }

    max
}

fn calc_distance(path: &[usize], distances: &Vec<Vec<usize>>) -> usize {
    let mut dist = 0;

    for i in 0..path.len() - 1 {
        let from = path[i];
        let to = path[i + 1];
        dist += distances[from][to];
    }

    dist
}

fn parse_distances(s: &str) -> Vec<Vec<usize>> {
    let cities = count_verticies(s);
    let mut distances = Vec::new();

    for _ in 0..cities {
        let mut row = Vec::new();
        for _ in 0..cities {
            row.push(0);
        }
        distances.push(row);
    }

    let mut lines = s.split("\n");
    for from in 0..cities {
        for to in from + 1..cities {
            if let Some(dist) = lines.next().and_then(parse_distance) {
                distances[from][to] = dist;
                distances[to][from] = dist;
            }
        }
    }

    distances
}

// 1  edge:   1              -> 2 vertices in total
// 3  edges:  2 + 1          -> 3 vertices in total
// 6  edges:  3 + 2 + 1      -> 4 vertices in total
// 10 edges:  4 + 3 + 2 + 1  -> 5 vertices in total
fn count_verticies(s: &str) -> usize {
    let edges = s.split("\n").count();

    let mut n = 0;
    let mut sum = 0;
    while sum < edges {
        n += 1;
        sum += n;
    }

    n + 1
}

fn parse_distance(s: &str) -> Option<usize> {
    let [_, dist] = split(s, " = ");
    dist.parse().ok()
}

fn split<'a>(s: &'a str, delimiter: &str) -> [&'a str; 2] {
    let splitted: Vec<_> = s.split(delimiter).collect();
    [splitted[0], splitted[1]]
}

fn get_test_input() -> &'static str {
    "London to Dublin = 464
London to Belfast = 518
Dublin to Belfast = 141"
}

fn get_input() -> &'static str {
    "Faerun to Tristram = 65
Faerun to Tambi = 129
Faerun to Norrath = 144
Faerun to Snowdin = 71
Faerun to Straylight = 137
Faerun to AlphaCentauri = 3
Faerun to Arbre = 149
Tristram to Tambi = 63
Tristram to Norrath = 4
Tristram to Snowdin = 105
Tristram to Straylight = 125
Tristram to AlphaCentauri = 55
Tristram to Arbre = 14
Tambi to Norrath = 68
Tambi to Snowdin = 52
Tambi to Straylight = 65
Tambi to AlphaCentauri = 22
Tambi to Arbre = 143
Norrath to Snowdin = 8
Norrath to Straylight = 23
Norrath to AlphaCentauri = 136
Norrath to Arbre = 115
Snowdin to Straylight = 101
Snowdin to AlphaCentauri = 84
Snowdin to Arbre = 96
Straylight to AlphaCentauri = 107
Straylight to Arbre = 14
AlphaCentauri to Arbre = 46"
}