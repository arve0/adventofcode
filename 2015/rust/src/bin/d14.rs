fn main() {
  let mut reindeers = vec![Reindeer::new(14, 10, 127), Reindeer::new(16, 11, 162)];

  solve(&mut reindeers, 1000);

  assert_eq!(reindeers[0].distance, 1120);
  assert_eq!(reindeers[1].distance, 1056);

  assert_eq!(reindeers[0].score, 312);
  assert_eq!(reindeers[1].score, 689);

  let mut reindeers = vec![
    Reindeer::new(22, 8,  165),
    Reindeer::new(8,  17, 114),
    Reindeer::new(18, 6,  103),
    Reindeer::new(25, 6,  145),
    Reindeer::new(11, 12, 125),
    Reindeer::new(21, 6,  121),
    Reindeer::new(18, 3,  50),
    Reindeer::new(20, 4,  75),
    Reindeer::new(7,  20, 119),
  ];

  solve(&mut reindeers, 2503);

  let max_distance = reindeers.iter().map(|r| r.distance).max().unwrap();
  println!("{}", max_distance);

  let max_score = reindeers.iter().map(|r| r.score).max().unwrap();
  println!("{}", max_score);
}

fn solve(reindeers: &mut Vec<Reindeer>, n: usize) {
  for _ in 0..n {
    for r in reindeers.iter_mut() {
      r.tick();
    }
    let max_distance = reindeers.iter().map(|r| r.distance).max().unwrap();
    reindeers.iter_mut()
      .filter(|r| r.distance == max_distance)
      .for_each(|r| r.score += 1);
  }
}

struct Reindeer {
  speed: usize,
  fly_time: usize,
  rest_time: usize,
  seconds: usize,
  distance: usize,
  score: usize,
}

impl Reindeer {
  fn new(speed: usize, fly_time: usize, rest_time: usize) -> Reindeer {
    Reindeer { speed, fly_time, rest_time, seconds: 0, distance: 0, score: 0 }
  }

  fn tick(&mut self) {
    if self.seconds < self.fly_time {
      self.distance += self.speed;
    }
    self.seconds += 1;
    if self.seconds >= self.fly_time + self.rest_time {
      self.seconds = 0;
    }
  }
}
