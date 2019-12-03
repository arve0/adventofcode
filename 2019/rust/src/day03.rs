use crate::lib;
use std::collections::HashSet;
use std::hash::{Hash, Hasher};

pub fn solve() {
    assert_eq!(distance_to_crossing(("R8,U5,L5,D3".to_owned(), "U7,R6,D4,L4".to_owned())), (6, 30));
    assert_eq!(distance_to_crossing(("R75,D30,R83,U83,L12,D49,R71,U7,L72".to_owned(), "U62,R66,U55,R34,D71,R55,D58,R83".to_owned())), (159, 610));
    assert_eq!(distance_to_crossing(("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51".to_owned(), "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7".to_owned())), (135, 410));

    let raw_input = lib::read_input("input03.txt", "\n");
    let input = (raw_input[0].clone(), raw_input[1].clone());
    let (part1, part2) = distance_to_crossing(input);

    println!("day 3 part 1: {}", part1);
    println!("day 3 part 1: {}", part2);
}

fn distance_to_crossing(wires: (String, String)) -> (i64, i64) {
    let paths = (trace_path(wires.0), trace_path(wires.1));
    closest_crossing(paths)
}

type Path = HashSet<Point>;
fn trace_path(wire: String) -> Path {
    let mut path = HashSet::new();
    let mut x: i64 = 0;
    let mut y: i64 = 0;
    let mut d: i64 = 0;

    wire.split(",")
        .for_each(|jump| {
            let direction = jump.get(0..1).unwrap();
            let mut distance: i64 = jump.get(1..).map(|x| x.parse().unwrap()).unwrap();

            let (dx, dy) = match direction {
                "U" => (0, 1),
                "R" => (1, 0),
                "D" => (0, -1),
                "L" => (-1, 0),
                _ => unreachable!(),
            };

            while distance > 0 {
                x += dx;
                y += dy;
                d += 1;
                path.insert(Point { x, y, d });
                distance -= 1;
            }
        });

    path
}

#[derive(Debug)]
struct Point {
    x: i64,
    y: i64,
    d: i64,
}

impl PartialEq for Point {
    fn eq(&self, other: &Point) -> bool {
        self.x == other.x && self.y == other.y
    }
}

impl Eq for Point {}

impl Hash for Point {
    fn hash<H: Hasher>(&self, state: &mut H) {
        self.x.hash(state);
        self.y.hash(state);
    }
}

fn closest_crossing(paths: (Path, Path)) -> (i64, i64) {
    let common_points = paths.0.iter()
        .filter(|p| paths.1.contains(p));

    let manhattan = common_points.clone()
        .map(dist_manhattan)
        .min()
        .unwrap();

    let by_wire = common_points
        .map(|p| {
            p.d.abs() + paths.1.get(p).unwrap().d.abs()
        })
        .min()
        .unwrap();

    (manhattan, by_wire)
}

fn dist_manhattan(point: &Point) -> i64 {
    point.x.abs() + point.y.abs()
}