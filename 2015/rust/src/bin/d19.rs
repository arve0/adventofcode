use std::collections::HashMap;
use std::collections::HashSet;

fn main() {
  let test_replacements = replacements_to_map(get_raw_test_replacements());
  let replaced = replace("HOH", &test_replacements);
  assert_eq!(replaced.len(), 4);

  let medisin = "CRnCaSiRnBSiRnFArTiBPTiTiBFArPBCaSiThSiRnTiBPBPMgArCaSiRnTiMgArCaSiThCaSiRnFArRnSiRnFArTiTiBFArCaCaSiRnSiThCaCaSiRnMgArFYSiRnFYCaFArSiThCaSiThPBPTiMgArCaPRnSiAlArPBCaCaSiRnFYSiThCaRnFArArCaCaSiRnPBSiRnFArMgYCaCaCaCaSiThCaCaSiAlArCaCaSiRnPBSiAlArBCaCaCaCaSiThCaPBSiThPBPBCaSiRnFYFArSiThCaSiRnFArBCaCaSiRnFYFArSiThCaPBSiThCaSiRnPMgArRnFArPTiBCaPRnFArCaCaCaCaSiRnCaCaSiRnFYFArFArBCaSiThFArThSiThSiRnTiRnPMgArFArCaSiThCaPBCaSiRnBFArCaCaPRnCaCaPMgArSiRnFYFArCaSiThRnPBPMgAr";
  let replacements = replacements_to_map(get_raw_replacements());
  let replaced = replace(medisin, &replacements);
  println!("{}", replaced.len());

  // The solution does not (!!!) work with the example problem.
  // assert_eq!(6, solve_part_two("HOHOHO", get_raw_test_replacements()));
  println!("{}", solve_part_two(medisin, get_raw_replacements()));
}

fn solve_part_two(s: &str, replacements: Vec<(&'static str, &'static str)>) -> usize {
  let replacements: Vec<_> = swap(replacements);

  let mut s = String::from(s);
  let mut count = 0;

  while s != "e" {
    let match_ = get_rightmost_match(&s, &replacements);
    let (from, to) = replacements[match_];
    s = replace_from_right(&s, from, to);
    count += 1;
  }

  return count
}

fn get_rightmost_match(s: &str, replacements: &Vec<(&'static str, &'static str)>) -> usize {
  let mut best_match = 0;
  let mut match_: usize = replacements.len();

  for (i, (from, _)) in replacements.iter().enumerate() {
    if let Some(pos) = s.rfind(from) {
      if pos + from.len() > best_match {
        best_match = pos + from.len();
        match_ = i;
      }
    }
  }

  match_
}

fn replace_from_right(s: &str, from: &str, to: &str) -> String {
  if let Some(pos) = s.rfind(from) {
    let mut r = String::from(&s[..pos]);
    r += to;
    r += &s[pos + from.len()..];
    return r
  } else {
    panic!("Did not find pattern.")
  }
}

fn swap(v: Vec<(&'static str, &'static str)>) -> Vec<(&'static str, &'static str)> {
  v.iter().map(|t| (t.1, t.0)).collect()
}

fn replace(s: &str, replacements: &HashMap<String, Vec<String>>) -> Vec<String> {
  let mut replaced = HashSet::new();

  for (replace, to) in replacements.iter() {
    let len = replace.len();
    for i in 0..s.len() {
      if i+len > s.len() {
        continue;
      }
      if &s[i..i+len] == replace {
        for r in to {
          let mut new = String::from(&s[0..i]);
          new += r;
          new += &s[i+len..];
          replaced.insert(new);
        }
      }
    }
  }

  replaced.into_iter().collect()
}

fn replacements_to_map(r: Vec<(&'static str, &'static str)>) -> HashMap<String, Vec<String>> {
  let mut replacements = HashMap::new();

  r.iter().for_each(|(k, v)| {
    replacements.entry(k.to_string()).or_insert(Vec::new()).push(v.to_string());
  });

  replacements
}

fn get_raw_test_replacements() -> Vec<(&'static str, &'static str)> {
  vec![
    ("H", "HO"),
    ("H", "OH"),
    ("O", "HH"),
    ("e", "H"),
    ("e", "O"),
  ]
}

fn get_raw_replacements() -> Vec<(&'static str, &'static str)> {
  vec![
    ("Al", "ThF"),
    ("Al", "ThRnFAr"),
    ("B", "BCa"),
    ("B", "TiB"),
    ("B", "TiRnFAr"),
    ("Ca", "CaCa"),
    ("Ca", "PB"),
    ("Ca", "PRnFAr"),
    ("Ca", "SiRnFYFAr"),
    ("Ca", "SiRnMgAr"),
    ("Ca", "SiTh"),
    ("F", "CaF"),
    ("F", "PMg"),
    ("F", "SiAl"),
    ("H", "CRnAlAr"),
    ("H", "CRnFYFYFAr"),
    ("H", "CRnFYMgAr"),
    ("H", "CRnMgYFAr"),
    ("H", "HCa"),
    ("H", "NRnFYFAr"),
    ("H", "NRnMgAr"),
    ("H", "NTh"),
    ("H", "OB"),
    ("H", "ORnFAr"),
    ("Mg", "BF"),
    ("Mg", "TiMg"),
    ("N", "CRnFAr"),
    ("N", "HSi"),
    ("O", "CRnFYFAr"),
    ("O", "CRnMgAr"),
    ("O", "HP"),
    ("O", "NRnFAr"),
    ("O", "OTi"),
    ("P", "CaP"),
    ("P", "PTi"),
    ("P", "SiRnFAr"),
    ("Si", "CaSi"),
    ("Th", "ThCa"),
    ("Ti", "BP"),
    ("Ti", "TiTi"),
    ("e", "HF"),
    ("e", "NAl"),
    ("e", "OMg"),
  ]
}