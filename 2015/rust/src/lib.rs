extern crate regex;

use regex::Regex;
use std::ops::Range;
use std::time::Instant;

pub fn get_numbers(text: &str) -> Vec<usize> {
    let re = Regex::new(r"\d{1,}").unwrap();
    re.captures_iter(text)
        .map(|d| d.get(0).unwrap().as_str().parse::<usize>().unwrap())
        .collect()
}

pub fn map_mut<T, F>(arr: &mut [T], range: Range<usize>, f: F)
where
    F: Fn(&T) -> T
{
    let splice = arr[range].iter_mut();

    for x in splice {
        *x = f(x);
    }
}

pub fn map_mut_2d<T, F>(arr: &mut Vec<Vec<T>>, xrange: Range<usize>, yrange: Range<usize>, f: F)
where
    F: Fn(&T) -> T
{
    arr[yrange].iter_mut().for_each(|r| map_mut(r, xrange.clone(), &f));
}

pub fn time_since(start: Instant) {
    let elapsed = start.elapsed();
    println!("{:?}", elapsed);
    println!("Elapsed: {} ms", elapsed.as_secs() * 1_000 + elapsed.subsec_millis() as u64);
}
