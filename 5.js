var m = require('md5');
var assert = require('assert');

var input = 'ffykfhsq';

function findPassword (key, length) {
  var i = 0;
  var password = '';

  while (password.length < length) {
    var h = m(key + i);
    if (h.slice(0, 5) === '00000') {
      debugger;
      password += h[5];
    }
    i += 1;
  }

  return password;
}

assert.equal(findPassword('abc', 3), '18f');

console.log(findPassword(input, 8));
