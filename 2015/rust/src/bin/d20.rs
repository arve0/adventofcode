fn main() {
    let wanted_presents = 34000000;
    let houses = create_houses(wanted_presents / 10);

    assert_eq!(houses[1], 10);
    assert_eq!(houses[2], 30);
    assert_eq!(houses[3], 40);
    assert_eq!(houses[4], 70);
    assert_eq!(houses[5], 60);
    assert_eq!(houses[6], 120);
    assert_eq!(houses[7], 80);
    assert_eq!(houses[8], 150);
    assert_eq!(houses[9], 130);

    let mut house = 1;

    while houses[house] < wanted_presents {
        house += 1;
    }

    println!("{}", house);
}

fn create_houses(max: usize) -> Vec<usize> {
    let mut houses: Vec<usize> = (0..max).map(|_| 0).collect();

    for elve in 1..max {
        for house in (elve..max).step_by(elve) {
            houses[house] += 10 * elve;
        }
    }

    houses
}