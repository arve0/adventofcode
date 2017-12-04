var crypto = require('crypto');

function md5 (str, hashes) {
  var h = crypto.createHash('md5');
  h = h.update(str).digest('hex');
  return hashes === 1 ? h : md5(h, hashes - 1);
}

function findKey (salt, number, hashes = 0) {
  hashes += 1;
  var keys = [];
  var last = [];
  var i = 0;
  while (i < 1000) {
    // keep 1000 up front
    last.push(md5(salt + i++, hashes));
  }
  while (keys.length < number) {
    i += 1;
    last[1000] = md5(salt + i, hashes);
    var hash = last.shift();
    var m = hash.match(/(.)\1{2}/);
    if (!m) {
      continue;
    }
    for (var j = 0; j < last.length; j += 1) {
      if (last[j].indexOf(m[1].repeat(5)) !== -1) {
        keys.push(hash);
        break;
      }
    }
    process.stdout.write(`\rsalt: ${salt} index: ${i} found: ${keys.length}`);
  }
  console.log();
  return i - 1000;
}

console.log(findKey('abc', 64));
console.log(findKey('cuanljph', 64));

console.log(findKey('abc', 64, 2016));
console.log(findKey('cuanljph', 64, 2016));
