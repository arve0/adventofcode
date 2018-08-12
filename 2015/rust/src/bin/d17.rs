fn main() {
    let containers = vec![20, 15, 10, 5, 5];
    let solutions = combinations(&containers, 25);
    assert_eq!(solutions.len(), 4);

    let containers = vec![11,30,47,31,32,36,3,1,5,3,32,36,15,11,46,26,28,1,19,3];
    let solutions = combinations(&containers, 150);
    println!("{}", solutions.len());

    let min_length = solutions.iter().map(|containers| containers.len()).min().unwrap();
    let amount_with_min_length = solutions.iter().filter(|c| c.len() == min_length).count();
    println!("{}", amount_with_min_length);
}

fn combinations(containers: &[usize], wanted_sum: usize) -> Vec<Vec<usize>> {
    let mut solution = Vec::new();

    for i in 0..containers.len() {
        let el = containers[i];
        if el == wanted_sum {
            solution.push(vec![el]);
            continue;
        } else if el > wanted_sum {
            continue;
        }
        combinations(&containers[i+1..], wanted_sum - el)
            .into_iter()
            .for_each(|mut minor_solution| {
                minor_solution.insert(0, el);
                solution.push(minor_solution);
            });
    }

    solution
}
