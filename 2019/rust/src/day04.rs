pub fn solve() {
    assert_eq!(digits(12345), vec![1,2,3,4,5]);
    assert_eq!(digits(-12345), vec![1,2,3,4,5]);
    assert_eq!(has_adjacent_digits(12245), true);
    assert_eq!(has_adjacent_digits(12345), false);
    assert_eq!(has_increasing_digits(111123), true);
    assert_eq!(has_increasing_digits(113123), false);

    assert_eq!(meets_part1_criterias(111111), true);
    assert_eq!(meets_part1_criterias(223450), false);
    assert_eq!(meets_part1_criterias(123789), false);

    assert_eq!(has_double_not_in_triple(112233), true);
    assert_eq!(has_double_not_in_triple(123444), false);
    assert_eq!(has_double_not_in_triple(111122), true);

    let mut part1 = 0;
    let mut part2 = 0;
    for n in 109165..576723 {
        if meets_part1_criterias(n) {
            part1 += 1;
            if has_double_not_in_triple(n) {
                part2 += 1;
            }
        }
    }
    println!("day 4 part 1: {}", part1);
    println!("day 4 part 2: {}", part2);
}

fn meets_part1_criterias(n: i64) -> bool {
    has_adjacent_digits(n) && has_increasing_digits(n)
}

fn has_adjacent_digits(n: i64) -> bool {
    let digits = digits(n);
    let mut prev = digits[0];

    for i in 1..digits.len() {
        if digits[i] == prev {
            return true;
        }
        prev = digits[i];
    }

    false
}

fn has_increasing_digits(n: i64) -> bool {
    let digits = digits(n);
    let mut prev = digits[0];

    for i in 1..digits.len() {
        if digits[i] < prev {
            return false;
        }
        prev = digits[i];
    }

    true
}

fn has_double_not_in_triple(n: i64) -> bool {
    let digits = digits(n);
    let mut prev = digits[0];

    'outer: for i in 1..digits.len() {
        if digits[i] == prev {
            let not_same_before = i == 1 || digits[i - 2] != prev;
            let not_same_after = i + 1 == digits.len() || digits[i + 1] != prev;
            if not_same_before && not_same_after {
                return true;
            }
        }
        prev = digits[i];
    }

    false
}

fn digits(mut n: i64) -> Vec<i64> {
    let mut digits = Vec::new();
    n = n.abs();

    while n != 0 {
        digits.insert(0, n % 10);
        n /= 10;
    }

    digits
}
