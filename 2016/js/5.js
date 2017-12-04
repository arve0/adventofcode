var crypto = require('crypto');

function m (str) {
  var h = crypto.createHash('md5');
  return h.update(str).digest('hex');
}

var assert = require('assert');

var input = 'ffykfhsq';

var t = Date.now();

function findPassword (key, length) {
  var i = -1;
  var password = [];
  var found = 0;
  process.stdout.write(arrToString(password, length));

  while (found !== length) {
    i += 1;
    var h = m(key + i);

    if (h.indexOf('00000') === 0) {
      debugger;
      var j = parseInt(h[5], 10);

      if (isNaN(j) || j >= length) {
        continue;
      }

      // avoid overwriting
      if (!password[j]) {
        password[j] = h[6];
        found += 1;
        process.stdout.write('\r' + arrToString(password, length) + ' time: ' + (Date.now() - t));
      }
    }
  }

  process.stdout.write('\n');

  return arrToString(password, length);
}

function arrToString (pass, length) {
  var res = [];
  for (var i = 0; i < length; i += 1) {
    if (!pass[i]) {
      res[i] = '_';
    } else {
      res[i] = pass[i];
    }
  }
  return res.join('');
}

assert.equal(findPassword('abc', 8), '05ace8e3');

console.log(findPassword(input, 8));
