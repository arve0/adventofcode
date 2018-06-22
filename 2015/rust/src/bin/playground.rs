use std::time::Instant;

fn main() {
    let start = Instant::now();

   std::thread::sleep(std::time::Duration::from_millis(1233));

    time_since(start);
}

fn time_since(start: Instant) {
    println!("{:?}", start);
    let elapsed = start.elapsed();
    println!("{:?}", elapsed);
    println!("Elapsed: {} ms", elapsed.as_secs() * 1_000 + elapsed.subsec_millis() as u64);

    elapsed.pretty_print();
}

struct Duration(std::time::Duration);

impl Duration {
    fn pretty_print(&self) {
        println!("{} s, {} ms, {}, µs {}, ns", self.secs, self.subsec_millis(), self.subsec_micros(), self.nanos);
    }
}