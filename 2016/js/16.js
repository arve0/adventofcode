function dragonCurve (str, length) {
  while (str.length < length) {
    str += '0' + str.split('')
                    .reverse()
                    .map(l => l === '0' ? '1' : '0')
                    .join('');
  }
  return str.slice(0, length);
}

function checkSum (str) {
  var sum = '';
  while (sum.length % 2 !== 1) {
    sum = '';
    for (var i = 0; i < str.length; i += 2) {
      switch (str[i] + str[i + 1]) {
        case '00':
        case '11':
          sum += '1';
          break;
        default:
          sum += '0';
          break;
      }
    }
    str = sum;
  }
  return sum;
}

var fill = dragonCurve('01110110101001000', 272);
var sum = checkSum(fill);
console.log(sum);

// part two
// 272 = 17 * 2^4
// 35651584 = 17 * 2^many
// ->> same checksum

