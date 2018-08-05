fn main() {
    assert_eq!(look_and_say("1"), "11");
    assert_eq!(look_and_say("11"), "21");
    assert_eq!(look_and_say("21"), "1211");
    assert_eq!(look_and_say("1211"), "111221");
    assert_eq!(look_and_say("111221"), "312211");

    let mut look = String::from("1321131112");
    for i in 0..50 {
        if i == 40 {
            println!("{}", look.len());
        }
        look = look_and_say(&look);
    }
    println!("{}", look.len());
}

fn look_and_say(s: &str) -> String {
    let mut say = String::new();

    let mut chars = s.chars();
    let mut prev = chars.next().unwrap();
    let mut count = 1;

    for ch in chars {
        if ch == prev {
            count += 1;
        } else {
            say.push_str(&count.to_string());
            say.push(prev);
            prev = ch;
            count = 1;
        }
    }

    say.push_str(&count.to_string());
    say.push(prev);

    say
}

fn count_digits(mut n: usize) -> usize {
    let mut c = 1;
    while n >= 10 {
        c += 1;
        n /= 10;
    }
    c
}