use crate::lib;
use std::collections::HashMap;

pub fn solve() {
    let map: Vec<_> = vec!["COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L"]
        .iter()
        .map(|&s| String::from(s))
        .collect();

    let map = to_map(map);
    let connected = connect_all(map);
    assert_eq!(count_orbits(connected), 42);

    let input = lib::read_input("input06.txt", "\n");
    let map = to_map(input);
    let connected = connect_all(map);
    let part1 = count_orbits(connected);
    println!("day 6 part 1: {}", part1);
}

fn count_orbits(orbits: Vec<Vec<String>>) -> usize {
    let mut count = 0;
    let mut counted = Vec::new();

    for orbit in orbits {
        for (n, planet) in orbit.iter().enumerate() {
            if !counted.contains(planet) {
                count += n;
                counted.push(planet.to_owned());
            }
        }
    }

    count
}

type Map = HashMap<String, Vec<String>>;
fn connect_all(mut map: Map) -> Vec<Vec<String>> {
    let mut connections = vec![vec!["COM".to_owned()]];

    while map.len() != 0 {
        connections = connections.into_iter()
            .flat_map(|c| {
                let last = c.last().unwrap();
                if let Some(planets) = map.remove(last) {
                    planets.into_iter()
                        .map(|planet| {
                            let mut c = c.clone();
                            c.push(planet);
                            c
                        })
                        .collect()
                } else {
                    vec![c]
                }
            })
            .collect()
    }

    connections
}

fn to_map(input: Vec<String>) -> Map {
    let mut map: Map = HashMap::new();
    let tuples = input.iter()
        .flat_map(|s| split(s));

    for (k, v) in tuples {
        let v = v.to_owned();

        if let Some(values) = map.get_mut(k) {
            values.push(v);
        } else {
            map.insert(k.to_owned(), vec![v]);
        }
    }

    map
}

fn split<'a>(s: &'a String) -> Option<(&'a str, &'a str)> {
    if s == "" {
        return None
    }

    let mut split = s.split(")");

    if let (Some(k), Some(v)) = (split.next(), split.next()) {
        Some((k, v))
    } else {
        eprintln!("Could not split {} by )", s);
        None
    }
}
