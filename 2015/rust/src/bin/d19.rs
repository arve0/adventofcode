use std::collections::HashMap;
use std::collections::HashSet;

fn main() {

  let replaced = replace("HOH", get_test_replacements());
  assert_eq!(replaced.len(), 4);

  let replaced = replace("CRnCaSiRnBSiRnFArTiBPTiTiBFArPBCaSiThSiRnTiBPBPMgArCaSiRnTiMgArCaSiThCaSiRnFArRnSiRnFArTiTiBFArCaCaSiRnSiThCaCaSiRnMgArFYSiRnFYCaFArSiThCaSiThPBPTiMgArCaPRnSiAlArPBCaCaSiRnFYSiThCaRnFArArCaCaSiRnPBSiRnFArMgYCaCaCaCaSiThCaCaSiAlArCaCaSiRnPBSiAlArBCaCaCaCaSiThCaPBSiThPBPBCaSiRnFYFArSiThCaSiRnFArBCaCaSiRnFYFArSiThCaPBSiThCaSiRnPMgArRnFArPTiBCaPRnFArCaCaCaCaSiRnCaCaSiRnFYFArFArBCaSiThFArThSiThSiRnTiRnPMgArFArCaSiThCaPBCaSiRnBFArCaCaPRnCaCaPMgArSiRnFYFArCaSiThRnPBPMgAr", get_replacements());
  println!("{}", replaced.len());
}

fn replace(s: &str, replacements: HashMap<String, Vec<String>>) -> HashSet<String> {
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

  replaced
}

fn get_test_replacements() -> HashMap<String, Vec<String>> {
  let mut replacements = HashMap::new();
  replacements.insert("H".to_string(), vec!["HO".to_string(), "OH".to_string()]);
  replacements.insert("O".to_string(), vec!["HH".to_string()]);
  replacements
}

fn get_replacements() -> HashMap<String, Vec<String>> {
  let mut replacements = HashMap::new();

  get_raw_replacements().iter().for_each(|(k, v)| {
    replacements.entry(k.to_string()).or_insert(Vec::new()).push(v.to_string());
  });

  replacements
}

fn get_raw_replacements() -> [(&'static str, &'static str); 43] {
  [
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
    ("e", "(OMg"),
  ]
}
