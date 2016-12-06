var assert = require('assert');

function splitIntoColumns (str) {
  var rows = str.split('\n');
  var numberOfColumns = rows[0].length;

  var columns = [];
  for (var j = 0; j < numberOfColumns; j += 1) {
    columns.push([]);
    for (var i = 0; i < rows.length; i += 1) {
      columns[j].push(rows[i][j]);
    }
  }
  return columns.map(c => c.join(''));
}

assert.deepEqual(splitIntoColumns(getTestInput())[0], 'ederatsrnnstvvde');

function countLetters (str) {
  var letters = {};
  // { 'a': 1, 'b': 3, ... }
  for (var i = 0; i < str.length; i += 1) {
    var letter = str[i];
    if (letter in letters) {
      letters[letter] += 1;
    } else {
      letters[letter] = 1;
    }
  }
  return letters;
}

function mostCommonLetter (str) {
  var letters = countLetters(str);
  var uniqKeys = new Set(Object.keys(letters));
  var sortedStr = Array.from(uniqKeys).sort((a, b) => {
    if (letters[a] > letters[b]) {
      return -1;
    }
    if (letters[a] < letters[b]) {
      return 1;
    }
    if (a < b) {
      return -1;
    }
    if (a > b) {
      return 1;
    }
    throw new Error(`Could not sort ${a} and ${b}.`);
  });
  return sortedStr[0];
}

assert(splitIntoColumns(getTestInput()).map(c => mostCommonLetter(c)).join(''), 'easter');

console.log(splitIntoColumns(getInput()).map(c => mostCommonLetter(c)).join(''));

// part two

function leastCommonLetter (str) {
  var letters = countLetters(str);
  var uniqKeys = new Set(Object.keys(letters));
  var sortedStr = Array.from(uniqKeys).sort((a, b) => {
    if (letters[a] > letters[b]) {
      return -1;
    }
    if (letters[a] < letters[b]) {
      return 1;
    }
    if (a < b) {
      return -1;
    }
    if (a > b) {
      return 1;
    }
    throw new Error(`Could not sort ${a} and ${b}.`);
  });
  return sortedStr[sortedStr.length - 1];
}

assert(splitIntoColumns(getTestInput()).map(c => leastCommonLetter(c)).join(''), 'advent');

console.log(splitIntoColumns(getInput()).map(c => leastCommonLetter(c)).join(''));

function getTestInput () {
  return `eedadn
drvtee
eandsr
raavrd
atevrs
tsrnev
sdttsa
rasrtv
nssdts
ntnada
svetve
tesnvt
vntsnd
vrdear
dvrsen
enarar`;
}

function getInput () {
  return `edayownz
qevxxipf
oyfsuaok
zfmvayzp
gthxucti
iqxyfvll
prkedskq
yqhhlxlf
atpwtvcn
nmkelcha
kyduptlo
xndlncrq
tipblras
yctuizxl
lsgooprx
dqxnjqqg
ssocughj
yaivjuxw
zcsknpig
fhzrgqrx
chkhrruy
icnkylam
kbqlhbvu
znjxbmph
qmcoeozq
jdhmdkwn
eihlergj
oxygpsiw
eahthlnd
ghclibzv
mhuuyxyn
vmdgtxbb
liwbkvko
bshezmju
itispews
gqapeypr
rscjseri
hjduhfhe
kvcgjclk
hosxlvmy
tlclgvua
riisgkkg
zeuhprjp
hvroersj
bextpjjm
nvnehksu
xdizemfw
rclldruz
yqlmidvv
unqprmbv
bbzhhwck
psneussu
vwioupfs
cqgxohsd
kmxmpxpi
ycgkyssk
hmlpirmz
gjwupihp
erxmlqjf
iywpocue
guvqdahd
xklbtvzq
fmfezcwj
wdttpdcl
eoaljfnd
tovgtunp
hpttqedt
vwozzoms
xotvgahm
abushckh
msdnfnxz
zdpibocq
kzjiijni
xigcngbb
raeqmzbv
yjabwdtc
qnnjxiqs
kffhcdbt
oxwdnsyq
jpkxkwgg
lunyifxj
zziecuwq
ppxcppnf
ezlghprw
mbyoczdv
ijhqlapc
zskkzaax
cavtwzww
ywhqysqa
gxixgaid
rdplryfx
vxzwidxk
yfpnmhnn
pbkyjzar
dqzgtcso
ilzvjgfc
diftsigx
zoeqphho
yufniiec
iiswsmrc
fjkbnzlz
jkoldwjd
dcuwtgyr
jvkubfei
wgfpgmge
cvslbjik
jfmkqujl
vunhzfdb
mxsyvstd
jneeyqte
mzbaxtqt
uhodcjil
qqinvvoa
hhjqwaog
kbxrwlop
xaifsdzd
ojndbnxs
cyvutfok
mubxvkds
wguoyxzh
wcwaauif
olgzrtab
qvsxggvh
ujvuwwzc
nuvwxepc
pydsjlgh
zvqtajky
dpmdjhnd
rcuhhmif
vfxzonrp
okhiinzj
mkcqcnnv
ieygctny
qyfjqlzm
tajvptkv
oltkvacv
hqmecybl
unpsmheo
kxmamiib
evggknyl
mfrvrykw
bqasfoun
jopiyvnd
nxamdqbn
lskjwhqt
rrmbyyte
elsvrfej
pudzkrcm
ltpdxcrc
rlqfxmfr
sdnpdswd
zhiyftpv
ksbkhiab
ogkezijn
uctbhbpy
jlkndedy
ryrlgbrh
zgqoacnp
zzqkqbcn
xholtory
liynoaqx
euwefqvm
ekmdkqlg
bmbfokxo
blovckfl
uxjjkoes
ywzprxog
uxvaegwv
mzwnwnzg
skenfyuq
dgarqgnr
qversoqf
cgbehjht
fqpzffoe
asmauutz
qegkxdas
utvsgjxe
eqtkqpjw
wgscmuel
sylgqbai
meekpavr
qawaeltl
pmfmzday
eacuapxf
ujjjpztx
jzdozhgv
iwtjotig
wyfvplbr
mpnusncj
jimlnzqr
ewixftvu
nchodvro
iioutsdt
emlphqzc
frurxhki
snpdeobb
pjqfbikx
ylwukgpi
gogxroix
wrwwcwij
vtaqqpah
blrbupmr
mqaqmfhz
gwuzsbmw
bfmstsdt
vfhbyfqb
xvithmha
krsruynd
lvdvkmso
iobpdwll
ialoymtw
zaokarah
xckbjyyw
ysqavfyg
zxxzzhkc
dhbplxba
ikdndwsj
dgrosyji
stydweit
rqithswc
mprjkbcm
tpefyiyd
ugwanchp
lfntfnvp
ldffntfm
kurlyldh
pujosrgy
okgdikeg
zxbintiq
awqffyyc
kzjcgkgl
dpstrbqq
hznvceti
grqvykxz
hpgsjjpn
yelqmlbz
ydenklms
fgtemjds
hwlqvvig
sncpgltw
grkosjlo
nbxjnfra
fxvsemon
souhaowz
pkcnohff
vhcreeba
fynixcza
inxtrakx
farxvioo
agsnldgu
rvvgqkvf
daabmava
fewhpwqc
dttqfpzj
vrgqbglu
ggsinjno
ahwxqpvb
ucxyogfk
oyejznyl
ojfgaztf
xcykczts
vuryincu
ixqgbsdj
acbanjtm
tdyqtrmo
nhnbzozm
tqzoqesh
cfcplowu
arbixqul
cnrplnfa
xmigrqle
rmjyyzeh
hpjhnoxt
lqzpfwlv
qefdfezf
yuypbdfq
mneruzvm
wxmydvis
xsfvldhb
vzxcaquj
aaysnxex
rkppoeey
jdozzlnr
bhwmkdiz
crpaljad
llqcfxwd
loonmljh
iwvywckw
sxslcqoh
ldcnmmwp
zedceaeu
deufsoyu
oomrzukn
kfasaxan
vpnmdimg
sotdxjpn
nslotgct
vblvivxh
busfjvlz
abcmroxw
bslpzufv
czvbqijh
wsdpwryu
ygtfjuyp
tlphfumq
rrdvvugh
cxiivwmf
majaarpb
woekjbuw
fappbyau
sebfoflc
mbfuexcc
xansapct
cduuxgyi
jbjcsblt
pntseavw
kmkqmsqz
rraranvr
vlbqtwck
xbhlqnqt
lgsbuagd
rbvtgaxj
blyabybe
henduein
qjncjzah
tqxhwqlw
mhrqqspx
bkydjpoq
tywauuvf
ouulbehj
gmeaujhk
wozdrlpz
afzilmuc
bqojkapy
fhxrteew
ulzwchev
uisqbsff
dyabtzax
uqchomer
zpumjhgy
tyrnstet
qidwkkgd
ahxyarzu
yeqiadpt
arzcxnjb
sedukbar
vehseved
mvrlcetm
foyxewke
wdceguqh
psktwwrf
qidupsru
ngijgwtx
ogcrudpu
kwhlixob
dzdtrvqa
outiqhrg
vpsuldwp
gnodzyfg
trrfkpoq
ppunsbnx
zcugptti
fxhheqbf
eddsaqgj
cobpfrjl
vwqfmlib
kzcefymt
njfyehdq
dcocnsyp
jdvkuxvr
bkbdxpus
umpilgyg
yvjgghfr
cqgujxms
svtucbvi
eghdzlve
htzgeocp
qyaqblby
otxzspak
nzvzuuzx
khzhlkhq
vnoalkux
oawnycto
ynlnxjka
ouowyusf
nfqzgjje
renfsdtp
jmlckpss
xnucnvla
wnphnmde
eszjzuim
cwxjbifm
rofbizjy
cwxeafvi
guorrfss
xsufaepj
lbncoixc
hpzziggn
tyltjgpi
ubegrqxz
bhtaoiuk
bkkjzrmu
irfinrlk
gmholyrx
tcuwtwmn
fxixgmeo
ltvxyudy
sxmmpdvc
ewqldfhz
tfafbwul
ciijowfk
mwemtqmv
gwojqcsg
hmfrvalw
szqbemsq
jkyaglii
zetwsswt
dujxheug
nwmebpqt
xzhdwgyy
ucmzfjzr
pdqsftob
rtjmvbyi
otnfglss
nfvkhhru
bxqcvvdo
qljvnqbd
wfjbwcgi
gdwzhdkd
sardfknc
wvhuqxsa
abgmwher
tfqrdsgm
flyjmfff
lprmhgzz
pjposbov
jyvmzmlx
zkacwauz
kgyothce
aytmcgnf
atiwoznr
jblykexy
nziwkcsb
bxejmnma
cjhhdzoj
ucgkmduk
jszgrfhh
gvcwuorc
czopwrwg
bilvjtju
uqgfdusi
mdjcqbyp
nmtsrvge
bpeaxibi
posabkfe
pfwhphds
jpycuzlh
qujmxewu
ehdiecja
xmyiuqmb
qvwitlyg
desikusy
pvaxwbkc
elkduxal
igmmotmo
qkkvmpqz
tikgdieo
tcevjbrq
poyymxnm
ortrilot
xmyrypdk
rtsqwswz
timjqxta
kgoybzuw
nduwmjcl
kybovset
prcuubkn
kzvwiybb
yrgwdwya
ibfhvdxt
ojzjsnxm
albohvtm
htemxqeo
wgkyrfrx
wopxooda
cbuxmavy
qlwakvjl
ftjfsgpb
useelmgi
sfwqxtcn
hjgsbkjr
laprvoum
xnxzfqww
ijginkgo
qimkncqq
svaectoj
gflciuue
dbrrxdan
gjrwzybe
fobzvyfv
xuevixmp
phxzveoo
lkkwygmo
vogtawhe
dnswsnzx
ayvdixkr
jkyvtkgj
gwcieicd
qepbzhlu
vibcltdk
zpofapqk
cjfsdthn
etnipjxn
qjcycocp
dsrggicy
snuzhjbm
ivbrvmjp
nczrvkkl
utzbrcwh
argkcywe
wkaybnsw
wndmwodp
mpltkkop
nkcadyyq
lbmkqzql
fzbnprqv
tkozordv
trmnahav
yiqelzvk
ljjwickh
htbzyahb
fmdomnzg
ndatgras
npplxesj
hwtyhtrk
jayhqwor
hthbwbma
sugktxhy
azrtyvpm
slzlctjb
hhlejgxq
ffmhvvdk
difyrfie
awifonbf
waexckwz
weatwffs`;
}
