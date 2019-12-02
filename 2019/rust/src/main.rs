mod lib;
mod day01;
mod day02;

fn main() {
    day01::solve();

    let (part1, part2) = day02::solve();
    println!("day 2 part 1: {}", part1);
    println!("day 2 part 2: {}", part2);
}
