fn main() {
  let mut test_ingredients = vec![
    Ingredient::new(-1, -2, 6, 3, 8),
    Ingredient::new(2, 3, -2, -1, 3),
  ];

  let score = solve(&mut test_ingredients);
  assert_eq!(score, 62842880);

  let score = solve_500_calories(&mut test_ingredients);
  assert_eq!(score, 57600000);

  let mut ingredients = vec![
    Ingredient::new(4, -2, 0, 0, 5),
    Ingredient::new(0, 5, -1, 0, 8),
    Ingredient::new(-1, 0, 5, 0, 6),
    Ingredient::new(0, 0, -2, 2, 1),
  ];

  let score = solve(&mut ingredients);
  println!("{}", score);

  let score = solve_500_calories(&mut ingredients);
  println!("{}", score);
}

fn solve(ingredients: &mut Vec<Ingredient>) -> usize {
  let mut max = 0;
  for amounts in ingredient_combinations(ingredients.len(), 100) {
    let score = calc_score(ingredients, &amounts);

    if score > max {
      max = score;
    }
  }
  max
}

fn ingredient_combinations(wanted_len: usize, wanted_sum: usize) -> Vec<Vec<isize>> {
    if wanted_len == 1 {
        vec![vec![wanted_sum as isize]]
    } else {
        let mut solution = Vec::new();
        for i in 1..wanted_sum - wanted_len {
            let mut minor_problem = ingredient_combinations(wanted_len - 1, wanted_sum - i);
            minor_problem.iter_mut().for_each(|row| row.push(i as isize));
            solution.append(&mut minor_problem);
        }
        solution
    }
}

fn calc_score(ingredients: &Vec<Ingredient>, amounts: &Vec<isize>) -> usize {
  let scores: Vec<_> = ingredients.iter()
    .zip(amounts)
    .map(|(i, a)| vec![i.capacity * a, i.durability * a, i.flavor * a, i.texture * a])
    .collect();

  let mut product = 1;
  for i in 0..4 {
    let sum: isize = scores.iter().map(|s| s[i]).sum();
    product *= if sum < 0 { 0 } else { sum as usize };
  }

  product
}

fn solve_500_calories(ingredients: &mut Vec<Ingredient>) -> usize {
  let mut max = 0;
  for amounts in ingredient_combinations(ingredients.len(), 100) {
    if calc_calories(ingredients, &amounts) != 500 {
      continue;
    }
    let score = calc_score(ingredients, &amounts);

    if score > max {
      max = score;
    }
  }
  max
}

fn calc_calories(ingredients: &Vec<Ingredient>, amounts: &Vec<isize>) -> usize {
  let s: isize = ingredients.iter()
    .zip(amounts)
    .map(|(i, a)| i.calories * a)
    .sum();

  s as usize
}

struct Ingredient {
  capacity: isize,
  durability: isize,
  flavor: isize,
  texture: isize,
  calories: isize,
}

impl Ingredient {
  fn new(capacity: isize, durability: isize, flavor: isize, texture: isize, calories: isize) -> Ingredient {
    Ingredient { capacity, durability, flavor, texture, calories }
  }
}
