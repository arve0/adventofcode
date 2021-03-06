fn main () {
  let wanted_aunt = Aunt{ n: 0, children: 3, cats: 7, samoyeds: 2, pomeranians: 3, akitas: 0, vizslas: 0, goldfish: 5, trees: 3, cars: 2, perfumes: 1 };

  let aunts = get_aunts();
  let a = aunts.iter().find(|aunt| aunt.is_match_to(&wanted_aunt));
  println!("{:?}", a);

  let a = aunts.iter()
    .find(|aunt| aunt.is_match_to_outdated_retroencabulator(&wanted_aunt));
  println!("{:?}", a);
}

#[derive(Debug)]
struct Aunt {
  n: usize,
  children: isize,
  cats: isize,
  samoyeds: isize,
  pomeranians: isize,
  akitas: isize,
  vizslas: isize,
  goldfish: isize,
  trees: isize,
  cars: isize,
  perfumes: isize,
}

impl Aunt {
  fn new(n: usize) -> Aunt {
    Aunt {
      n,
      children: -1,
      cats: -1,
      samoyeds: -1,
      pomeranians: -1,
      akitas: -1,
      vizslas: -1,
      goldfish: -1,
      trees: -1,
      cars: -1,
      perfumes: -1,
    }
  }
  fn is_match_to(&self, other: &Aunt) -> bool {
    (self.children == -1 || self.children == other.children) &&
    (self.cats == -1 || self.cats == other.cats) &&
    (self.samoyeds == -1 || self.samoyeds == other.samoyeds) &&
    (self.pomeranians == -1 || self.pomeranians == other.pomeranians) &&
    (self.akitas == -1 || self.akitas == other.akitas) &&
    (self.vizslas == -1 || self.vizslas == other.vizslas) &&
    (self.goldfish == -1 || self.goldfish == other.goldfish) &&
    (self.trees == -1 || self.trees == other.trees) &&
    (self.cars == -1 || self.cars == other.cars) &&
    (self.perfumes == -1 || self.perfumes == other.perfumes)
  }
  fn is_match_to_outdated_retroencabulator(&self, other: &Aunt) -> bool {
    (self.children == -1 || self.children == other.children) &&
    (self.samoyeds == -1 || self.samoyeds == other.samoyeds) &&
    (self.akitas == -1 || self.akitas == other.akitas) &&
    (self.vizslas == -1 || self.vizslas == other.vizslas) &&
    (self.cars == -1 || self.cars == other.cars) &&
    (self.perfumes == -1 || self.perfumes == other.perfumes) &&
    (self.cats == -1 || self.cats > other.cats) &&
    (self.trees == -1 || self.trees > other.trees) &&
    (self.pomeranians == -1 || self.pomeranians < other.pomeranians) &&
    (self.goldfish == -1 || self.goldfish < other.goldfish)
  }
  fn children(mut self, children: isize) -> Aunt {
    self.children = children;
    self
  }
  fn cats(mut self, cats: isize) -> Aunt {
    self.cats = cats;
    self
  }
  fn samoyeds(mut self, samoyeds: isize) -> Aunt {
    self.samoyeds = samoyeds;
    self
  }
  fn pomeranians(mut self, pomeranians: isize) -> Aunt {
    self.pomeranians = pomeranians;
    self
  }
  fn akitas(mut self, akitas: isize) -> Aunt {
    self.akitas = akitas;
    self
  }
  fn vizslas(mut self, vizslas: isize) -> Aunt {
    self.vizslas = vizslas;
    self
  }
  fn goldfish(mut self, goldfish: isize) -> Aunt {
    self.goldfish = goldfish;
    self
  }
  fn trees(mut self, trees: isize) -> Aunt {
    self.trees = trees;
    self
  }
  fn cars(mut self, cars: isize) -> Aunt {
    self.cars = cars;
    self
  }
  fn perfumes(mut self, perfumes: isize) -> Aunt {
    self.perfumes = perfumes;
    self
  }
}

fn get_aunts() -> Vec<Aunt> {
  vec![
    Aunt::new(1).children(1).cars(8).vizslas(7),
    Aunt::new(2).akitas(10).perfumes(10).children(5),
    Aunt::new(3).cars(5).pomeranians(4).vizslas(1),
    Aunt::new(4).goldfish(5).children(8).perfumes(3),
    Aunt::new(5).vizslas(2).akitas(7).perfumes(6),
    Aunt::new(6).vizslas(0).akitas(1).perfumes(2),
    Aunt::new(7).perfumes(8).cars(4).goldfish(10),
    Aunt::new(8).perfumes(7).children(2).cats(1),
    Aunt::new(9).pomeranians(3).goldfish(10).trees(10),
    Aunt::new(10).akitas(7).trees(8).pomeranians(4),
    Aunt::new(11).goldfish(1).perfumes(4).cars(6),
    Aunt::new(12).samoyeds(6).trees(6).perfumes(2),
    Aunt::new(13).akitas(10).pomeranians(0).vizslas(2),
    Aunt::new(14).cars(2).perfumes(3).children(4),
    Aunt::new(15).goldfish(2).children(8).cars(5),
    Aunt::new(16).goldfish(9).cars(0).vizslas(5),
    Aunt::new(17).cats(5).trees(6).perfumes(6),
    Aunt::new(18).cars(0).perfumes(8).pomeranians(7),
    Aunt::new(19).trees(2).goldfish(5).perfumes(4),
    Aunt::new(20).akitas(4).vizslas(4).trees(0),
    Aunt::new(21).pomeranians(7).trees(0).goldfish(10),
    Aunt::new(22).cars(4).vizslas(0).perfumes(3),
    Aunt::new(23).vizslas(8).trees(1).akitas(2),
    Aunt::new(24).children(7).trees(0).akitas(1),
    Aunt::new(25).goldfish(3).akitas(2).trees(2),
    Aunt::new(26).pomeranians(4).vizslas(4).samoyeds(2),
    Aunt::new(27).cars(0).trees(8).akitas(5),
    Aunt::new(28).perfumes(6).cats(0).cars(2),
    Aunt::new(29).trees(7).akitas(1).vizslas(1),
    Aunt::new(30).perfumes(9).cars(9).trees(10),
    Aunt::new(31).pomeranians(5).akitas(9).samoyeds(1),
    Aunt::new(32).pomeranians(10).vizslas(5).goldfish(5),
    Aunt::new(33).vizslas(2).akitas(3).trees(7),
    Aunt::new(34).goldfish(10).perfumes(0).samoyeds(7),
    Aunt::new(35).akitas(6).cats(7).perfumes(10),
    Aunt::new(36).pomeranians(8).vizslas(7).akitas(6),
    Aunt::new(37).goldfish(2).cars(10).children(7),
    Aunt::new(38).goldfish(2).perfumes(3).cars(7),
    Aunt::new(39).trees(9).vizslas(10).cars(5),
    Aunt::new(40).goldfish(1).pomeranians(0).trees(2),
    Aunt::new(41).trees(2).goldfish(6).vizslas(3),
    Aunt::new(42).akitas(1).cars(3).vizslas(3),
    Aunt::new(43).akitas(1).pomeranians(1).vizslas(3),
    Aunt::new(44).goldfish(7).akitas(3).vizslas(10),
    Aunt::new(45).akitas(8).samoyeds(8).goldfish(2),
    Aunt::new(46).trees(0).vizslas(4).cars(9),
    Aunt::new(47).cars(9).trees(10).perfumes(4),
    Aunt::new(48).akitas(0).vizslas(5).perfumes(4),
    Aunt::new(49).goldfish(9).trees(1).cars(4),
    Aunt::new(50).goldfish(2).perfumes(5).cars(2),
    Aunt::new(51).samoyeds(1).goldfish(2).perfumes(7),
    Aunt::new(52).cars(0).perfumes(4).goldfish(8),
    Aunt::new(53).goldfish(9).vizslas(2).akitas(9),
    Aunt::new(54).trees(1).goldfish(9).children(5),
    Aunt::new(55).cars(0).akitas(5).trees(4),
    Aunt::new(56).trees(4).samoyeds(5).children(9),
    Aunt::new(57).children(0).vizslas(8).cars(3),
    Aunt::new(58).trees(4).pomeranians(5).akitas(5),
    Aunt::new(59).vizslas(10).cats(3).children(2),
    Aunt::new(60).cats(6).vizslas(2).cars(2),
    Aunt::new(61).akitas(1).vizslas(0).children(4),
    Aunt::new(62).akitas(4).trees(9).children(10),
    Aunt::new(63).pomeranians(6).vizslas(6).cars(4),
    Aunt::new(64).perfumes(8).pomeranians(1).children(8),
    Aunt::new(65).perfumes(3).goldfish(6).trees(5),
    Aunt::new(66).goldfish(10).akitas(8).vizslas(4),
    Aunt::new(67).vizslas(10).samoyeds(3).trees(2),
    Aunt::new(68).samoyeds(4).cars(7).perfumes(3),
    Aunt::new(69).perfumes(2).goldfish(0).trees(2),
    Aunt::new(70).trees(8).vizslas(7).akitas(6),
    Aunt::new(71).cars(2).children(7).perfumes(3),
    Aunt::new(72).cars(1).akitas(9).perfumes(0),
    Aunt::new(73).vizslas(4).akitas(7).cars(5),
    Aunt::new(74).samoyeds(3).cars(3).akitas(2),
    Aunt::new(75).trees(2).cars(1).vizslas(7),
    Aunt::new(76).samoyeds(9).perfumes(1).trees(6),
    Aunt::new(77).trees(6).perfumes(10).cars(7),
    Aunt::new(78).trees(0).children(8).vizslas(5),
    Aunt::new(79).vizslas(0).trees(0).samoyeds(1),
    Aunt::new(80).trees(6).goldfish(8).perfumes(0),
    Aunt::new(81).samoyeds(8).pomeranians(6).akitas(5),
    Aunt::new(82).vizslas(6).perfumes(9).akitas(4),
    Aunt::new(83).cats(0).vizslas(3).pomeranians(10),
    Aunt::new(84).cars(4).perfumes(6).samoyeds(5),
    Aunt::new(85).vizslas(7).trees(5).goldfish(7),
    Aunt::new(86).goldfish(2).trees(2).vizslas(1),
    Aunt::new(87).trees(6).goldfish(10).pomeranians(4),
    Aunt::new(88).vizslas(1).akitas(0).perfumes(8),
    Aunt::new(89).goldfish(8).akitas(3).vizslas(7),
    Aunt::new(90).vizslas(9).akitas(7).perfumes(9),
    Aunt::new(91).children(7).cars(7).trees(9),
    Aunt::new(92).vizslas(10).akitas(8).goldfish(1),
    Aunt::new(93).goldfish(7).vizslas(2).pomeranians(0),
    Aunt::new(94).cats(2).samoyeds(6).pomeranians(3),
    Aunt::new(95).samoyeds(4).children(4).pomeranians(10),
    Aunt::new(96).pomeranians(9).cats(1).goldfish(3),
    Aunt::new(97).trees(1).akitas(6).goldfish(1),
    Aunt::new(98).vizslas(7).akitas(2).perfumes(7),
    Aunt::new(99).pomeranians(6).perfumes(2).trees(1),
    Aunt::new(100).cars(3).children(9).trees(10),
    Aunt::new(101).children(0).perfumes(0).vizslas(3),
    Aunt::new(102).cars(4).goldfish(5).children(2),
    Aunt::new(103).pomeranians(3).perfumes(7).cats(8),
    Aunt::new(104).akitas(0).perfumes(5).vizslas(5),
    Aunt::new(105).akitas(7).vizslas(2).samoyeds(8),
    Aunt::new(106).goldfish(7).perfumes(0).cats(8),
    Aunt::new(107).cats(6).pomeranians(9).cars(6),
    Aunt::new(108).akitas(3).vizslas(10).cats(5),
    Aunt::new(109).akitas(10).perfumes(2).cars(7),
    Aunt::new(110).goldfish(7).pomeranians(1).trees(1),
    Aunt::new(111).akitas(10).samoyeds(6).vizslas(6),
    Aunt::new(112).cats(6).akitas(7).trees(9),
    Aunt::new(113).akitas(1).trees(9).vizslas(8),
    Aunt::new(114).vizslas(2).cats(1).cars(4),
    Aunt::new(115).akitas(0).trees(5).goldfish(7),
    Aunt::new(116).goldfish(2).trees(10).akitas(2),
    Aunt::new(117).cars(4).goldfish(10).perfumes(5),
    Aunt::new(118).cars(5).perfumes(6).trees(0),
    Aunt::new(119).perfumes(5).vizslas(1).cats(0),
    Aunt::new(120).perfumes(8).akitas(9).vizslas(4),
    Aunt::new(121).samoyeds(2).vizslas(7).perfumes(6),
    Aunt::new(122).children(6).trees(9).perfumes(2),
    Aunt::new(123).cars(7).akitas(0).pomeranians(0),
    Aunt::new(124).akitas(7).cats(8).vizslas(5),
    Aunt::new(125).goldfish(3).trees(1).cars(4),
    Aunt::new(126).cars(4).perfumes(3).akitas(0),
    Aunt::new(127).children(10).vizslas(5).akitas(9),
    Aunt::new(128).akitas(3).samoyeds(2).cats(8),
    Aunt::new(129).cats(8).akitas(1).vizslas(8),
    Aunt::new(130).trees(4).cars(6).perfumes(6),
    Aunt::new(131).akitas(7).perfumes(6).goldfish(9),
    Aunt::new(132).akitas(6).vizslas(7).trees(1),
    Aunt::new(133).akitas(5).vizslas(7).children(9),
    Aunt::new(134).cars(8).goldfish(4).pomeranians(4),
    Aunt::new(135).samoyeds(1).pomeranians(6).akitas(4),
    Aunt::new(136).perfumes(10).goldfish(1).cars(3),
    Aunt::new(137).cars(3).samoyeds(6).vizslas(7),
    Aunt::new(138).samoyeds(10).akitas(3).perfumes(4),
    Aunt::new(139).perfumes(10).vizslas(2).goldfish(7),
    Aunt::new(140).samoyeds(7).cars(1).trees(2),
    Aunt::new(141).children(6).cats(5).cars(9),
    Aunt::new(142).cats(0).trees(1).akitas(10),
    Aunt::new(143).samoyeds(4).cars(0).children(7),
    Aunt::new(144).trees(0).cars(4).perfumes(8),
    Aunt::new(145).goldfish(7).cars(5).trees(1),
    Aunt::new(146).perfumes(7).cars(7).goldfish(0),
    Aunt::new(147).trees(2).goldfish(7).vizslas(5),
    Aunt::new(148).samoyeds(8).perfumes(1).trees(0),
    Aunt::new(149).vizslas(2).samoyeds(5).trees(0),
    Aunt::new(150).akitas(4).perfumes(4).pomeranians(2),
    Aunt::new(151).trees(2).cars(0).goldfish(10),
    Aunt::new(152).goldfish(7).vizslas(0).trees(0),
    Aunt::new(153).children(9).cats(0).pomeranians(10),
    Aunt::new(154).cars(6).goldfish(10).akitas(5),
    Aunt::new(155).perfumes(9).trees(2).akitas(3),
    Aunt::new(156).pomeranians(9).perfumes(5).cars(9),
    Aunt::new(157).akitas(0).trees(2).cars(7),
    Aunt::new(158).goldfish(10).trees(8).akitas(7),
    Aunt::new(159).akitas(5).trees(10).cars(10),
    Aunt::new(160).akitas(3).trees(5).cars(8),
    Aunt::new(161).samoyeds(2).cars(7).perfumes(4),
    Aunt::new(162).cars(6).vizslas(10).pomeranians(5),
    Aunt::new(163).cars(10).perfumes(6).vizslas(9),
    Aunt::new(164).pomeranians(7).cars(4).vizslas(2),
    Aunt::new(165).goldfish(9).vizslas(3).trees(1),
    Aunt::new(166).goldfish(1).samoyeds(3).trees(1),
    Aunt::new(167).vizslas(4).goldfish(7).cats(5),
    Aunt::new(168).children(1).cars(5).samoyeds(7),
    Aunt::new(169).trees(1).samoyeds(3).goldfish(6),
    Aunt::new(170).goldfish(2).cars(3).perfumes(9),
    Aunt::new(171).cars(4).goldfish(0).trees(6),
    Aunt::new(172).cats(8).perfumes(6).trees(1),
    Aunt::new(173).akitas(9).goldfish(7).cars(10),
    Aunt::new(174).vizslas(2).trees(0).akitas(1),
    Aunt::new(175).perfumes(3).vizslas(8).akitas(4),
    Aunt::new(176).perfumes(0).akitas(6).goldfish(3),
    Aunt::new(177).perfumes(6).children(1).goldfish(10),
    Aunt::new(178).cars(5).vizslas(3).children(10),
    Aunt::new(179).perfumes(3).trees(8).cats(9),
    Aunt::new(180).perfumes(8).vizslas(4).trees(7),
    Aunt::new(181).perfumes(7).vizslas(9).samoyeds(4),
    Aunt::new(182).vizslas(9).trees(4).pomeranians(4),
    Aunt::new(183).trees(9).cars(3).goldfish(5),
    Aunt::new(184).perfumes(2).cars(4).vizslas(3),
    Aunt::new(185).children(10).akitas(10).cats(9),
    Aunt::new(186).cars(5).samoyeds(0).trees(0),
    Aunt::new(187).trees(2).goldfish(3).cars(4),
    Aunt::new(188).goldfish(3).vizslas(1).cats(6),
    Aunt::new(189).trees(2).pomeranians(10).cars(7),
    Aunt::new(190).perfumes(10).akitas(3).samoyeds(0),
    Aunt::new(191).cats(5).vizslas(6).akitas(6),
    Aunt::new(192).samoyeds(5).trees(1).perfumes(8),
    Aunt::new(193).pomeranians(0).akitas(9).cats(0),
    Aunt::new(194).trees(1).goldfish(0).perfumes(10),
    Aunt::new(195).perfumes(2).akitas(7).cars(5),
    Aunt::new(196).perfumes(5).samoyeds(8).cars(1),
    Aunt::new(197).vizslas(2).pomeranians(9).trees(1),
    Aunt::new(198).trees(8).vizslas(6).children(8),
    Aunt::new(199).pomeranians(4).cars(7).vizslas(5),
    Aunt::new(200).trees(0).perfumes(10).akitas(10),
    Aunt::new(201).cats(9).akitas(4).vizslas(0),
    Aunt::new(202).goldfish(9).pomeranians(9).cats(6),
    Aunt::new(203).cars(5).perfumes(5).trees(2),
    Aunt::new(204).pomeranians(7).children(2).akitas(6),
    Aunt::new(205).samoyeds(7).pomeranians(7).children(6),
    Aunt::new(206).trees(1).cars(1).pomeranians(4),
    Aunt::new(207).goldfish(2).perfumes(5).trees(0),
    Aunt::new(208).perfumes(2).samoyeds(4).trees(1),
    Aunt::new(209).cars(8).perfumes(6).goldfish(9),
    Aunt::new(210).perfumes(4).cars(8).samoyeds(3),
    Aunt::new(211).perfumes(2).cars(8).trees(9),
    Aunt::new(212).trees(7).perfumes(2).akitas(5),
    Aunt::new(213).children(3).goldfish(5).vizslas(0),
    Aunt::new(214).akitas(6).goldfish(0).children(0),
    Aunt::new(215).trees(8).akitas(3).goldfish(1),
    Aunt::new(216).goldfish(6).perfumes(8).akitas(3),
    Aunt::new(217).children(7).trees(2).vizslas(6),
    Aunt::new(218).goldfish(8).samoyeds(4).pomeranians(6),
    Aunt::new(219).goldfish(8).samoyeds(0).children(9),
    Aunt::new(220).perfumes(1).cars(8).vizslas(6),
    Aunt::new(221).perfumes(9).cars(10).children(10),
    Aunt::new(222).perfumes(9).vizslas(1).trees(0),
    Aunt::new(223).goldfish(1).akitas(2).vizslas(8),
    Aunt::new(224).samoyeds(8).akitas(7).vizslas(4),
    Aunt::new(225).goldfish(1).cars(4).perfumes(10),
    Aunt::new(226).goldfish(9).trees(4).perfumes(5),
    Aunt::new(227).vizslas(5).trees(4).goldfish(7),
    Aunt::new(228).cars(1).cats(10).perfumes(4),
    Aunt::new(229).vizslas(8).cars(10).akitas(4),
    Aunt::new(230).cats(1).children(8).vizslas(3),
    Aunt::new(231).perfumes(7).cats(6).samoyeds(7),
    Aunt::new(232).cars(3).children(9).perfumes(7),
    Aunt::new(233).vizslas(1).samoyeds(2).children(2),
    Aunt::new(234).trees(1).samoyeds(8).children(2),
    Aunt::new(235).trees(6).akitas(9).goldfish(7),
    Aunt::new(236).children(10).trees(0).samoyeds(8),
    Aunt::new(237).pomeranians(4).trees(1).goldfish(2),
    Aunt::new(238).vizslas(4).akitas(2).cars(0),
    Aunt::new(239).goldfish(9).cars(10).perfumes(4),
    Aunt::new(240).perfumes(3).vizslas(6).trees(6),
    Aunt::new(241).pomeranians(6).akitas(4).trees(2),
    Aunt::new(242).cars(8).perfumes(5).children(7),
    Aunt::new(243).trees(4).perfumes(7).cars(3),
    Aunt::new(244).perfumes(6).akitas(1).vizslas(7),
    Aunt::new(245).akitas(3).perfumes(9).samoyeds(0),
    Aunt::new(246).pomeranians(3).vizslas(9).samoyeds(1),
    Aunt::new(247).cars(0).goldfish(7).cats(2),
    Aunt::new(248).trees(5).goldfish(6).perfumes(3),
    Aunt::new(249).trees(0).pomeranians(7).perfumes(9),
    Aunt::new(250).cars(9).trees(1).goldfish(10),
    Aunt::new(251).perfumes(3).cars(8).trees(7),
    Aunt::new(252).cars(5).akitas(7).trees(8),
    Aunt::new(253).perfumes(7).akitas(3).trees(8),
    Aunt::new(254).goldfish(8).samoyeds(1).vizslas(7),
    Aunt::new(255).perfumes(3).cars(4).children(6),
    Aunt::new(256).perfumes(9).trees(8).children(7),
    Aunt::new(257).trees(8).children(6).cars(4),
    Aunt::new(258).vizslas(1).trees(10).goldfish(9),
    Aunt::new(259).vizslas(5).trees(6).goldfish(9),
    Aunt::new(260).trees(0).goldfish(6).cars(7),
    Aunt::new(261).cars(1).perfumes(4).goldfish(9),
    Aunt::new(262).cars(7).goldfish(9).cats(9),
    Aunt::new(263).cars(0).children(5).goldfish(8),
    Aunt::new(264).cars(2).akitas(8).trees(0),
    Aunt::new(265).perfumes(9).children(8).samoyeds(7),
    Aunt::new(266).cats(1).children(1).vizslas(10),
    Aunt::new(267).vizslas(8).children(2).trees(6),
    Aunt::new(268).akitas(10).vizslas(3).cats(2),
    Aunt::new(269).children(4).goldfish(1).cats(6),
    Aunt::new(270).vizslas(5).cars(9).akitas(9),
    Aunt::new(271).vizslas(5).children(4).akitas(3),
    Aunt::new(272).cars(1).goldfish(0).vizslas(0),
    Aunt::new(273).goldfish(10).samoyeds(1).akitas(2),
    Aunt::new(274).goldfish(10).children(2).pomeranians(0),
    Aunt::new(275).children(0).vizslas(1).samoyeds(6),
    Aunt::new(276).children(1).vizslas(3).samoyeds(1),
    Aunt::new(277).perfumes(4).cats(6).children(10),
    Aunt::new(278).pomeranians(7).goldfish(3).cars(4),
    Aunt::new(279).perfumes(5).goldfish(9).trees(7),
    Aunt::new(280).goldfish(6).trees(5).perfumes(8),
    Aunt::new(281).cars(2).akitas(1).vizslas(7),
    Aunt::new(282).vizslas(4).akitas(3).children(8),
    Aunt::new(283).pomeranians(8).akitas(9).vizslas(4),
    Aunt::new(284).samoyeds(10).trees(10).pomeranians(2),
    Aunt::new(285).akitas(9).perfumes(7).goldfish(6),
    Aunt::new(286).akitas(2).vizslas(7).goldfish(10),
    Aunt::new(287).pomeranians(8).cars(6).samoyeds(5),
    Aunt::new(288).pomeranians(1).trees(0).goldfish(0),
    Aunt::new(289).trees(10).samoyeds(1).children(0),
    Aunt::new(290).cats(10).samoyeds(6).trees(0),
    Aunt::new(291).vizslas(9).trees(6).goldfish(5),
    Aunt::new(292).cats(4).perfumes(8).cars(3),
    Aunt::new(293).goldfish(10).perfumes(10).cats(0),
    Aunt::new(294).cats(7).trees(6).akitas(4),
    Aunt::new(295).vizslas(8).cars(1).akitas(6),
    Aunt::new(296).vizslas(5).akitas(10).trees(1),
    Aunt::new(297).pomeranians(8).samoyeds(5).vizslas(4),
    Aunt::new(298).perfumes(10).children(5).vizslas(2),
    Aunt::new(299).cars(10).akitas(7).cats(5),
    Aunt::new(300).trees(1).perfumes(7).cars(7),
    Aunt::new(301).cars(9).vizslas(1).perfumes(3),
    Aunt::new(302).perfumes(9).vizslas(1).akitas(5),
    Aunt::new(303).akitas(9).trees(1).goldfish(10),
    Aunt::new(304).children(10).vizslas(6).pomeranians(8),
    Aunt::new(305).trees(3).goldfish(6).cats(9),
    Aunt::new(306).cars(5).perfumes(9).vizslas(5),
    Aunt::new(307).children(0).goldfish(7).trees(2),
    Aunt::new(308).trees(9).samoyeds(4).cars(0),
    Aunt::new(309).cats(8).vizslas(2).perfumes(3),
    Aunt::new(310).cars(6).pomeranians(6).vizslas(6),
    Aunt::new(311).vizslas(6).akitas(7).cats(10),
    Aunt::new(312).trees(0).goldfish(7).cars(0),
    Aunt::new(313).perfumes(5).akitas(5).cars(2),
    Aunt::new(314).akitas(10).vizslas(3).samoyeds(8),
    Aunt::new(315).cars(3).perfumes(1).goldfish(8),
    Aunt::new(316).pomeranians(6).goldfish(9).perfumes(1),
    Aunt::new(317).goldfish(4).akitas(6).cars(2),
    Aunt::new(318).perfumes(8).vizslas(8).akitas(0),
    Aunt::new(319).akitas(10).cars(5).vizslas(6),
    Aunt::new(320).vizslas(4).akitas(3).cats(4),
    Aunt::new(321).goldfish(4).akitas(8).cars(8),
    Aunt::new(322).pomeranians(5).vizslas(7).cats(1),
    Aunt::new(323).perfumes(1).trees(6).goldfish(0),
    Aunt::new(324).goldfish(6).trees(10).cars(10),
    Aunt::new(325).akitas(2).samoyeds(6).trees(9),
    Aunt::new(326).vizslas(4).akitas(7).cars(9),
    Aunt::new(327).children(3).perfumes(4).cars(1),
    Aunt::new(328).akitas(9).perfumes(6).cars(10),
    Aunt::new(329).perfumes(2).goldfish(0).trees(1),
    Aunt::new(330).vizslas(10).pomeranians(7).goldfish(6),
    Aunt::new(331).trees(3).vizslas(8).cars(3),
    Aunt::new(332).akitas(2).cats(1).goldfish(8),
    Aunt::new(333).cars(6).trees(2).vizslas(0),
    Aunt::new(334).samoyeds(7).cars(7).trees(3),
    Aunt::new(335).cats(7).children(1).perfumes(8),
    Aunt::new(336).akitas(5).goldfish(10).vizslas(5),
    Aunt::new(337).cats(3).vizslas(0).akitas(10),
    Aunt::new(338).perfumes(8).cars(1).trees(8),
    Aunt::new(339).cars(4).samoyeds(8).children(2),
    Aunt::new(340).goldfish(9).pomeranians(1).samoyeds(1),
    Aunt::new(341).akitas(3).trees(0).goldfish(2),
    Aunt::new(342).perfumes(4).vizslas(8).pomeranians(9),
    Aunt::new(343).akitas(4).cars(5).goldfish(4),
    Aunt::new(344).samoyeds(5).cats(4).trees(0),
    Aunt::new(345).samoyeds(4).cars(8).akitas(2),
    Aunt::new(346).akitas(3).vizslas(10).perfumes(10),
    Aunt::new(347).goldfish(10).akitas(4).cars(1),
    Aunt::new(348).perfumes(10).cats(4).vizslas(5),
    Aunt::new(349).akitas(2).vizslas(4).cars(7),
    Aunt::new(350).akitas(5).vizslas(5).cars(6),
    Aunt::new(351).vizslas(8).perfumes(6).cars(3),
    Aunt::new(352).cars(10).vizslas(0).goldfish(10),
    Aunt::new(353).cars(10).perfumes(5).children(7),
    Aunt::new(354).vizslas(6).akitas(3).samoyeds(9),
    Aunt::new(355).akitas(2).perfumes(7).cars(10),
    Aunt::new(356).cars(10).perfumes(7).children(6),
    Aunt::new(357).akitas(4).cars(8).trees(1),
    Aunt::new(358).trees(2).cars(1).goldfish(2),
    Aunt::new(359).vizslas(5).cars(9).trees(4),
    Aunt::new(360).perfumes(4).akitas(3).cars(3),
    Aunt::new(361).children(3).akitas(2).cats(5),
    Aunt::new(362).cars(8).cats(4).akitas(10),
    Aunt::new(363).cats(2).trees(1).vizslas(4),
    Aunt::new(364).vizslas(2).pomeranians(5).samoyeds(9),
    Aunt::new(365).samoyeds(2).akitas(7).goldfish(9),
    Aunt::new(366).goldfish(8).trees(7).cats(2),
    Aunt::new(367).perfumes(2).vizslas(6).trees(5),
    Aunt::new(368).cars(5).samoyeds(0).perfumes(6),
    Aunt::new(369).samoyeds(10).trees(10).vizslas(1),
    Aunt::new(370).trees(2).vizslas(3).cars(4),
    Aunt::new(371).akitas(6).pomeranians(2).cats(4),
    Aunt::new(372).trees(2).perfumes(3).goldfish(9),
    Aunt::new(373).vizslas(5).children(0).pomeranians(6),
    Aunt::new(374).trees(1).vizslas(8).perfumes(10),
    Aunt::new(375).cars(0).akitas(6).children(0),
    Aunt::new(376).akitas(1).vizslas(0).trees(0),
    Aunt::new(377).samoyeds(10).cats(5).pomeranians(0),
    Aunt::new(378).goldfish(3).pomeranians(7).cats(7),
    Aunt::new(379).perfumes(0).cats(0).trees(8),
    Aunt::new(380).perfumes(4).samoyeds(1).akitas(7),
    Aunt::new(381).akitas(4).pomeranians(2).children(4),
    Aunt::new(382).vizslas(9).akitas(4).trees(10),
    Aunt::new(383).trees(1).vizslas(10).akitas(6),
    Aunt::new(384).trees(3).akitas(8).goldfish(3),
    Aunt::new(385).goldfish(6).perfumes(2).children(9),
    Aunt::new(386).children(10).akitas(7).goldfish(7),
    Aunt::new(387).goldfish(3).vizslas(10).perfumes(5),
    Aunt::new(388).children(4).trees(0).cars(2),
    Aunt::new(389).trees(0).cats(3).goldfish(10),
    Aunt::new(390).samoyeds(9).pomeranians(0).cats(6),
    Aunt::new(391).samoyeds(10).trees(3).akitas(4),
    Aunt::new(392).akitas(9).goldfish(10).perfumes(7),
    Aunt::new(393).goldfish(6).cars(2).akitas(9),
    Aunt::new(394).trees(4).goldfish(9).vizslas(7),
    Aunt::new(395).vizslas(4).samoyeds(1).goldfish(6),
    Aunt::new(396).vizslas(5).cats(0).samoyeds(1),
    Aunt::new(397).goldfish(7).cats(0).trees(7),
    Aunt::new(398).cars(10).akitas(1).vizslas(7),
    Aunt::new(399).samoyeds(10).cats(6).goldfish(6),
    Aunt::new(400).cats(6).samoyeds(0).trees(2),
    Aunt::new(401).trees(1).children(4).goldfish(2),
    Aunt::new(402).cats(8).vizslas(4).children(3),
    Aunt::new(403).cars(9).perfumes(8).pomeranians(2),
    Aunt::new(404).goldfish(8).trees(2).cars(5),
    Aunt::new(405).perfumes(1).pomeranians(5).vizslas(5),
    Aunt::new(406).perfumes(6).trees(2).pomeranians(6),
    Aunt::new(407).trees(0).goldfish(6).cars(6),
    Aunt::new(408).trees(0).samoyeds(7).goldfish(9),
    Aunt::new(409).samoyeds(10).goldfish(6).pomeranians(0),
    Aunt::new(410).perfumes(5).vizslas(6).trees(0),
    Aunt::new(411).goldfish(2).trees(2).pomeranians(0),
    Aunt::new(412).pomeranians(4).perfumes(8).cats(8),
    Aunt::new(413).vizslas(4).cars(5).akitas(1),
    Aunt::new(414).perfumes(2).trees(8).goldfish(7),
    Aunt::new(415).akitas(3).trees(1).perfumes(3),
    Aunt::new(416).cars(7).trees(1).perfumes(8),
    Aunt::new(417).cars(5).goldfish(5).trees(1),
    Aunt::new(418).cars(9).goldfish(4).samoyeds(2),
    Aunt::new(419).pomeranians(8).akitas(1).goldfish(6),
    Aunt::new(420).cars(0).cats(0).children(8),
    Aunt::new(421).akitas(10).goldfish(1).vizslas(8),
    Aunt::new(422).children(8).vizslas(6).samoyeds(10),
    Aunt::new(423).samoyeds(3).goldfish(10).vizslas(8),
    Aunt::new(424).cars(3).children(7).goldfish(4),
    Aunt::new(425).cars(9).perfumes(9).goldfish(8),
    Aunt::new(426).akitas(5).trees(10).vizslas(10),
    Aunt::new(427).vizslas(10).cars(3).akitas(7),
    Aunt::new(428).cats(6).perfumes(5).goldfish(10),
    Aunt::new(429).goldfish(7).trees(5).vizslas(10),
    Aunt::new(430).perfumes(3).trees(7).cars(3),
    Aunt::new(431).cars(2).vizslas(1).akitas(6),
    Aunt::new(432).pomeranians(8).perfumes(5).cars(3),
    Aunt::new(433).children(8).cars(0).perfumes(7),
    Aunt::new(434).samoyeds(0).vizslas(9).akitas(10),
    Aunt::new(435).akitas(3).vizslas(8).cats(4),
    Aunt::new(436).goldfish(5).trees(8).samoyeds(8),
    Aunt::new(437).cars(10).samoyeds(9).goldfish(7),
    Aunt::new(438).samoyeds(5).akitas(7).perfumes(9),
    Aunt::new(439).goldfish(10).perfumes(5).cars(0),
    Aunt::new(440).pomeranians(1).samoyeds(9).children(4),
    Aunt::new(441).vizslas(4).perfumes(2).cats(5),
    Aunt::new(442).trees(0).pomeranians(3).cars(7),
    Aunt::new(443).akitas(0).cars(2).vizslas(10),
    Aunt::new(444).children(1).akitas(9).trees(0),
    Aunt::new(445).cars(5).perfumes(7).goldfish(9),
    Aunt::new(446).akitas(0).perfumes(1).vizslas(2),
    Aunt::new(447).vizslas(7).perfumes(0).cars(5),
    Aunt::new(448).vizslas(6).goldfish(10).trees(0),
    Aunt::new(449).cars(7).vizslas(7).trees(3),
    Aunt::new(450).pomeranians(4).akitas(4).vizslas(8),
    Aunt::new(451).cats(4).perfumes(8).children(3),
    Aunt::new(452).samoyeds(8).akitas(9).cars(1),
    Aunt::new(453).cars(8).akitas(5).vizslas(2),
    Aunt::new(454).vizslas(9).perfumes(4).akitas(4),
    Aunt::new(455).akitas(3).goldfish(2).vizslas(6),
    Aunt::new(456).cars(4).perfumes(5).goldfish(10),
    Aunt::new(457).trees(9).pomeranians(4).goldfish(10),
    Aunt::new(458).pomeranians(1).perfumes(9).children(6),
    Aunt::new(459).samoyeds(0).goldfish(8).vizslas(6),
    Aunt::new(460).cars(10).goldfish(8).samoyeds(8),
    Aunt::new(461).akitas(8).goldfish(9).vizslas(2),
    Aunt::new(462).cars(1).vizslas(2).akitas(8),
    Aunt::new(463).goldfish(2).akitas(4).samoyeds(10),
    Aunt::new(464).children(5).perfumes(5).cars(5),
    Aunt::new(465).perfumes(9).trees(0).samoyeds(6),
    Aunt::new(466).akitas(5).goldfish(3).cats(6),
    Aunt::new(467).perfumes(3).goldfish(0).trees(4),
    Aunt::new(468).goldfish(2).children(4).trees(1),
    Aunt::new(469).cars(0).perfumes(8).children(7),
    Aunt::new(470).vizslas(8).cats(5).samoyeds(9),
    Aunt::new(471).pomeranians(7).trees(2).goldfish(3),
    Aunt::new(472).goldfish(8).akitas(4).perfumes(5),
    Aunt::new(473).perfumes(2).pomeranians(3).cars(8),
    Aunt::new(474).samoyeds(0).akitas(7).pomeranians(6),
    Aunt::new(475).vizslas(7).perfumes(1).trees(6),
    Aunt::new(476).vizslas(3).samoyeds(1).perfumes(10),
    Aunt::new(477).cars(6).perfumes(5).vizslas(2),
    Aunt::new(478).pomeranians(1).goldfish(3).akitas(7),
    Aunt::new(479).goldfish(10).trees(0).cars(3),
    Aunt::new(480).cats(3).akitas(5).vizslas(8),
    Aunt::new(481).pomeranians(5).vizslas(2).trees(3),
    Aunt::new(482).cars(8).samoyeds(10).goldfish(10),
    Aunt::new(483).pomeranians(3).vizslas(6).goldfish(5),
    Aunt::new(484).perfumes(7).vizslas(4).akitas(7),
    Aunt::new(485).goldfish(1).trees(0).perfumes(10),
    Aunt::new(486).goldfish(6).perfumes(0).akitas(10),
    Aunt::new(487).cats(2).akitas(10).trees(1),
    Aunt::new(488).akitas(1).goldfish(3).cars(7),
    Aunt::new(489).goldfish(3).akitas(6).vizslas(6),
    Aunt::new(490).goldfish(8).perfumes(2).akitas(2),
    Aunt::new(491).trees(4).vizslas(8).perfumes(6),
    Aunt::new(492).cars(9).perfumes(3).cats(0),
    Aunt::new(493).trees(3).vizslas(6).goldfish(7),
    Aunt::new(494).trees(8).samoyeds(1).perfumes(5),
    Aunt::new(495).children(9).akitas(8).vizslas(4),
    Aunt::new(496).vizslas(2).pomeranians(1).perfumes(7),
    Aunt::new(497).trees(2).akitas(4).vizslas(6),
    Aunt::new(498).akitas(8).pomeranians(7).trees(0),
    Aunt::new(499).perfumes(6).goldfish(3).vizslas(7),
    Aunt::new(500).cars(1).perfumes(6).vizslas(1),
  ]
}
