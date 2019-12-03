use crate::lib;

pub fn solve() {
    assert_eq!(fuel_for_mass(12), 2);
    assert_eq!(fuel_for_mass(14), 2);
    assert_eq!(fuel_for_mass(1969), 654);
    assert_eq!(fuel_for_mass(100756), 33583);

    let input: Vec<i64> = lib::read_input("input01.txt", "\n")
        .iter()
        .flat_map(|n| n.parse())
        .collect();

    // day 1, part 1
    let fuel = input.iter()
        .fold(0, |total, &mass| total + fuel_for_mass(mass));

    println!("day 1 part 1: {}", fuel);

    // day 1, part 2
    assert_eq!(fuel_for_mass_and_fuel(12), 2);
    assert_eq!(fuel_for_mass_and_fuel(1969), 966);
    assert_eq!(fuel_for_mass_and_fuel(100756), 50346);

    let fuel = input.iter()
        .fold(0, |total, &mass| total + fuel_for_mass_and_fuel(mass));

    println!("day 1 part 2: {}", fuel);
}

fn fuel_for_mass(mass: i64) -> i64 {
    mass / 3 - 2
}

fn fuel_for_mass_and_fuel(mut mass: i64) -> i64 {
    let mut total = 0;
    while mass > 6 {
        mass = fuel_for_mass(mass);
        total += mass
    }
    total
}
