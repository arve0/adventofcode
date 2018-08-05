fn main() {
    assert_eq!(IncrementalString::new().to_string(), "aaaaaaaa");
    assert_eq!(IncrementalString::from("aaaaaaaa").to_string(), "aaaaaaaa");
    assert_eq!(IncrementalString::from("qwertyaa").to_string(), "qwertyaa");

    let mut s = IncrementalString::from("aaabaaaa");
    s.inc();
    assert_eq!(s.to_string(), "aaabaaab");

    for _ in 0..26 { s.inc(); }
    assert_eq!(s.to_string(), "aaabaabb");

    assert_eq!(IncrementalString::from("qwertyaa").has_straight(), false);
    assert_eq!(IncrementalString::from("abcaaaaa").has_straight(), true);
    assert_eq!(IncrementalString::from("aaaaastu").has_straight(), true);

    assert_eq!(IncrementalString::from("qwertyaa").has_bad_letters(), false);
    assert_eq!(IncrementalString::from("qweriyaa").has_bad_letters(), true);
    assert_eq!(IncrementalString::from("qweroyaa").has_bad_letters(), true);
    assert_eq!(IncrementalString::from("qwerlyaa").has_bad_letters(), true);

    assert_eq!(IncrementalString::from("qwertyaa").has_two_non_overlapping_pairs(), false);
    assert_eq!(IncrementalString::from("aabbccdd").has_two_non_overlapping_pairs(), true);
    assert_eq!(IncrementalString::from("aaabcdef").has_two_non_overlapping_pairs(), false);

    assert_eq!(IncrementalString::from("hijklmmn").is_safe(), false);
    assert_eq!(IncrementalString::from("abbceffg").is_safe(), false);
    assert_eq!(IncrementalString::from("abbcegjk").is_safe(), false);
    assert_eq!(IncrementalString::from("abcdffaa").is_safe(), true);

    s = IncrementalString::from("abcdefgh");
    while !s.is_safe() { s.inc(); }
    assert_eq!(s.to_string(), "abcdffaa");

    s = IncrementalString::from("hepxcrrq");
    while !s.is_safe() { s.inc(); }
    println!("{}", s.to_string());

    s.inc();
    while !s.is_safe() { s.inc(); }
    println!("{}", s.to_string());
}

struct IncrementalString {
    data: usize,
    _pos: u32,
}

impl IncrementalString {
    fn inc(&mut self) {
        self.data += 1;
    }

    fn to_string(&mut self) -> String {
        self.map(|ch| ch as char)
            .collect()
    }

    fn new() -> Self {
        IncrementalString { data: 0, _pos: 0 }
    }

    fn from(s: &str) -> Self {
        let sum = s.chars()
            .rev()
            .map(|ch| ch as usize - 'a' as usize)
            .enumerate()
            .map(|(i, ch)| ch * ALPHABET_SIZE.pow(i as u32))
            .sum();

        IncrementalString { data: sum, _pos: 0 }
    }

    fn is_safe(&mut self) -> bool {
        self.has_straight() && !self.has_bad_letters() && self.has_two_non_overlapping_pairs()
    }

    fn has_straight(&mut self) -> bool {
        let mut prev = self.next().unwrap();
        let mut count = 1;
        for ch in self {
            if prev + 1 == ch {
                count += 1;
            } else {
                count = 1;
            }
            if count == 3 {
                return true;
            }
            prev = ch;
        }
        false
    }

    fn has_bad_letters(&mut self) -> bool {
        let s = self.to_string();
        s.contains('i') || s.contains('o') || s.contains('l')
    }

    fn has_two_non_overlapping_pairs(&mut self) -> bool {
        let s = self.to_string();
        let mut pairs = 0;

        let mut prev_was_pair = false;
        for i in 0..(s.len() - 1) {
            if prev_was_pair {
                prev_was_pair = false;
                continue;
            }

            let [a, b] = [&s[i..i + 1], &s[i + 1..i + 2]];
            if a == b {
                pairs += 1;
                prev_was_pair = true;
            }

            if pairs == 2 { return true }
        }

        false
    }
}

static ALPHABET_SIZE: usize = 26;
impl Iterator for IncrementalString {
    type Item = u8;

    fn next(&mut self) -> Option<u8> {
        if self._pos == 8 {
            self._pos = 0;
            None
        } else {
            let i = 7 - self._pos;
            self._pos += 1;
            let mut ch = 'a' as u8;
            ch += (self.data % ALPHABET_SIZE.pow(i + 1) / ALPHABET_SIZE.pow(i)) as u8;
            Some(ch)
        }
    }
}