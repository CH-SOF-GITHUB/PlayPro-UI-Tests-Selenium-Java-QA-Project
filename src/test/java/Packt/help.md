Selenium Automation Testing:

Learn-Automation-Testing-with-Java-and-Selenium-Webdriver - Public
🌐  https://github.com/PacktPublishing/Learn-Automation-Testing-with-Java-and-Selenium-Webdriver



Java Programming Basics:

Java-Programming-Certification-Course
🌐  https://github.com/PacktPublishing/Java-Programming-Certification-Course/blob/main/Codes/2.%20Java%20Setup%20VS%20Code/Demo1.java


# JDK vs JRE vs JVM

- JVM (Java Virtual Machine) runs Java bytecode.
- JRE = JVM + Libraries + Other Components
- JDK = JRE + Compilers + Debuggers

# Concepts
- Object Oriented Program is a bunch of objects sending messages to each other.
- Important Concepts are Object, Class, State (How do we represent state?) and Behavior
- Object
    - Has a Type or Class
    - Made up of other objects
    - Has an interface
        - Defines what messages it can receive 


# Primitives Data Types And Alternatives
Last login: Mon Jan 29 11:22:26 on ttys001
Rangas-MacBook-Pro:~ rangaraokaranam$ Byte.SIZE
-bash: Byte.SIZE: command not found
Rangas-MacBook-Pro:~ rangaraokaranam$ jshell
|  Welcome to JShell -- Version 9.0.1
|  For an introduction type: /help intro

jshell> Byte.SIZE
$1 ==> 8

jshell> Byte.BYTES
$2 ==> 1

jshell> Byte.MAX_VALUE
$3 ==> 127

jshell> Byte.MIN_VALUE
$4 ==> -128

jshell> Short.BYTES
$5 ==> 2

jshell> Integer.BYTES
$6 ==> 4

jshell> Long.BYTES
$7 ==> 8

jshell> Integer.MAX_VALUE
$8 ==> 2147483647

jshell> Short.MAX_VALUE
$9 ==> 32767

jshell> int i = 560;
i ==> 560

jshell> long l = 540
l ==> 540

jshell>

jshell>

jshell> Byte.SIZE
$12 ==> 8

jshell> Byte.MIN_VALUE
$13 ==> -128

jshell> Byte.MAX_VALUE
$14 ==> 127

jshell> Short.BYTES
$15 ==> 2

jshell> Integer.BYTES
$16 ==> 4

jshell> Long.BYTES
$17 ==> 8

jshell> Integer.MAX_VALUE
$18 ==> 2147483647

jshell> int i = 100000;
i ==> 100000

jshell> long l = 50000000000;
|  Error:
|  integer number too large: 50000000000
|  long l = 50000000000;
|           ^

jshell> long l = 50000000000l;
l ==> 50000000000

jshell> i = l
|  Error:
|  incompatible types: possible lossy conversion from long to int
|  i = l
|      ^

jshell> i = (int) l
i ==> -1539607552

jshell> l = i
l ==> -1539607552

jshell> void clear() { System.out.print("\033[H\033[2J ");}
|  created method clear()

jshell> clear()


jshell> int eight = 010;
eight ==> 8

jshell> int sixteen = 0x10;
sixteen ==> 16

jshell> int fifteen = 0XF;
fifteen ==> 15

jshell> int eight = 099;
|  Error:
|  integer number too large: 099
|  int eight = 099;
|              ^

jshell> int eight = 08;
|  Error:
|  integer number too large: 08
|  int eight = 08;
|              ^

jshell> int big = 0XBBAACC;
big ==> 12298956

jshell> byte b = 128
|  Error:
|  incompatible types: possible lossy conversion from int to byte
|  byte b = 128;
|           ^-^

jshell> short s = 123456
|  Error:
|  incompatible types: possible lossy conversion from int to short
|  short s = 123456;
|            ^----^

jshell> Short.MAX_VALUE
$33 ==> 32767

jshell> int i = 3456
i ==> 3456

jshell> short s = i
|  Error:
|  incompatible types: possible lossy conversion from int to short
|  short s = i;
|            ^

jshell> short s = (int) i
|  Error:
|  incompatible types: possible lossy conversion from int to short
|  short s = (int) i;
|            ^-----^

jshell> short s = (short) i
s ==> 3456

jshell> int i1 = s
i1 ==> 3456

jshell> clear()


jshell> int i = 10;
i ==> 10

jshell> int j = i++;
j ==> 10

jshell> i
i ==> 11

jshell> int i = 10;
i ==> 10

jshell> int j = ++i;
j ==> 11

jshell> i
i ==> 11

jshell> int i = 10;
i ==> 10

jshell> int j = i--;
j ==> 10

jshell> i
i ==> 9

jshell> int i = 10;
i ==> 10

jshell> int j = --i;
j ==> 9

jshell> i
i ==> 9

jshell> clear()


jshell> 34.5
$51 ==> 34.5

jshell> 34.56789
$52 ==> 34.56789

jshell> float f = 34.5
|  Error:
|  incompatible types: possible lossy conversion from double to float
|  float f = 34.5;
|            ^--^

jshell> float f = 34.5f
f ==> 34.5

jshell> float f = 34.5F
f ==> 34.5

jshell> double dbl = 34.5678
dbl ==> 34.5678

jshell> float f2 = dbl
|  Error:
|  incompatible types: possible lossy conversion from double to float
|  float f2 = dbl;
|             ^-^

jshell> float f2 = (float)dbl
f2 ==> 34.5678

jshell> dbl++
$57 ==> 34.5678

jshell> dbl--
$58 ==> 35.5678

jshell> dbl % 5
$59 ==> 4.567799999999998

jshell> int i = f2
|  Error:
|  incompatible types: possible lossy conversion from float to int
|  int i = f2;
|          ^^

jshell> int i = (int) f2
i ==> 34

jshell> float f = i;
f ==> 34.0

jshell> clear()


jshell> 34.56789876 + 34.2234
$63 ==> 68.79129875999999

jshell> BigDecimal number1 = new BigDecimal("34.56789876");
number1 ==> 34.56789876

jshell> BigDecimal number2 = new BigDecimal("34.2234");
number2 ==> 34.2234

jshell> number1.add(number2);
$66 ==> 68.79129876

jshell> BigDecimal number3 = number1.add(number2);
number3 ==> 68.79129876

jshell> number1
number1 ==> 34.56789876

jshell> number1.
abs(                     add(                     byteValue()              
byteValueExact()         compareTo(               divide(                  
divideAndRemainder(      divideToIntegralValue(   doubleValue()            
equals(                  floatValue()             getClass()               
hashCode()               intValue()               intValueExact()          
longValue()              longValueExact()         max(                     
min(                     movePointLeft(           movePointRight(          
multiply(                negate(                  notify()                 
notifyAll()              plus(                    pow(                     
precision()              remainder(               round(                   
scale()                  scaleByPowerOfTen(       setScale(                
shortValue()             shortValueExact()        signum()                 
sqrt(                    stripTrailingZeros()     subtract(                
toBigInteger()           toBigIntegerExact()      toEngineeringString()    
toPlainString()          toString()               ulp()                    
unscaledValue()          wait(

jshell> BigDecimal number1 = new BigDecimal("34.56789876");
number1 ==> 34.56789876

jshell> BigDecimal number2 = new BigDecimal("34.2234");
number2 ==> 34.2234

jshell> BigDecimal number10 = new BigDecimal(34.2234);
number10 ==> 34.223399999999998044586391188204288482666015625

jshell> BigDecimal number11 = new BigDecimal(34.56789876);
number11 ==> 34.56789875999999850364474696107208728790283203125

jshell> BigDecimal number11 = new BigDecimal("34.56789876");
number11 ==> 34.56789876

jshell> BigDecimal number11 = new BigDecimal("34.56789876");
number11 ==> 34.56789876

jshell> number10 + number11
|  Error:
|  bad operand types for binary operator '+'
|    first type:  java.math.BigDecimal
|    second type: java.math.BigDecimal
|  number10 + number11
|  ^-----------------^

jshell> number10.add(number11)
$76 ==> 68.791298759999998044586391188204288482666015625

jshell> number10.multiply(number11)
$77 ==> 1183.03102642298393240546033666760195046663284301757812500

jshell> clear()


jshell> BigDecimal number = new BigDecimal("11.5");
number ==> 11.5

jshell> BigDecimal number2 = new BigDecimal("23.45678");
number2 ==> 23.45678

jshell> number.add(number2)
$81 ==> 34.95678

jshell> int i = 5
i ==> 5

jshell> number.add(i)
|  Error:
|  incompatible types: int cannot be converted to java.math.BigDecimal
|  number.add(i)
|             ^

jshell> number.add(new BigDecimal(i))
$83 ==> 16.5

jshell> number.multiply(new BigDecimal(i))
$84 ==> 57.5

jshell> number.divide(100)
|  Error:
|  incompatible types: int cannot be converted to java.math.BigDecimal
|  number.divide(100)
|                ^-^

jshell> number.divide(new BigDecimal(100))
$85 ==> 0.115

jshell> number.divide(new BigDecimal("100.012"))
|  java.lang.ArithmeticException thrown: Non-terminating decimal expansion; no exact representable decimal result.
|        at BigDecimal.divide (BigDecimal.java:1716)
|        at (#87:1)

jshell> number.divide(new BigDecimal("100.12"))
|  java.lang.ArithmeticException thrown: Non-terminating decimal expansion; no exact representable decimal result.
|        at BigDecimal.divide (BigDecimal.java:1716)
|        at (#88:1)

jshell> number.divide(new BigDecimal("100.1"))
|  java.lang.ArithmeticException thrown: Non-terminating decimal expansion; no exact representable decimal result.
|        at BigDecimal.divide (BigDecimal.java:1716)
|        at (#89:1)

jshell> number.divide(new BigDecimal("1001"))
|  java.lang.ArithmeticException thrown: Non-terminating decimal expansion; no exact representable decimal result.
|        at BigDecimal.divide (BigDecimal.java:1716)
|        at (#90:1)

jshell> number.divide(new BigDecimal("100"))
$91 ==> 0.115

jshell> clear()


jshell> new BigDecimal(
$1        $12       $13       $14       $15       $16       $17       $18       
$2        $3        $33       $4        $5        $51       $52       $57       
$58       $59       $6        $63       $7        $8        $9        big       
c         dbl       eight     f         f2        fifteen   i         i1        
j         l         s         sixteen

Signatures:
BigDecimal(char[] in, int offset, int len)
BigDecimal(char[] in, int offset, int len, MathContext mc)
BigDecimal(char[] in)
BigDecimal(char[] in, MathContext mc)
BigDecimal(String val)
BigDecimal(String val, MathContext mc)
BigDecimal(double val)
BigDecimal(double val, MathContext mc)
BigDecimal(BigInteger val)
BigDecimal(BigInteger val, MathContext mc)
BigDecimal(BigInteger unscaledVal, int scale)
BigDecimal(BigInteger unscaledVal, int scale, MathContext mc)
BigDecimal(int val)
BigDecimal(int val, MathContext mc)
BigDecimal(long val)
BigDecimal(long val, MathContext mc)

<press tab again to see documentation>

jshell> new BigDecimal(
BigDecimal(char[] in, int offset, int len)
Translates a character array representation of a BigDecimal into a BigDecimal ,
accepting the same sequence of characters as the #BigDecimal(String) constructor,
while allowing a sub-array to be specified.

Parameters:
in - char array that is the source of characters.
offset - first character in the array to inspect.
len - number of characters to consider.

Thrown Exceptions:
NumberFormatException - if in is not a valid representation of a BigDecimal or the
defined subarray is not wholly within in .

<press tab to see next documentation>

jshell> new BigDecimal(
BigDecimal(char[] in, int offset, int len, MathContext mc)
Translates a character array representation of a BigDecimal into a BigDecimal ,
accepting the same sequence of characters as the #BigDecimal(String) constructor,
while allowing a sub-array to be specified and with rounding according to the
context settings.

Parameters:
in - char array that is the source of characters.
offset - first character in the array to inspect.
len - number of characters to consider.
mc - the context to use.

Thrown Exceptions:
ArithmeticException - if the result is inexact but the rounding mode is UNNECESSARY
.
NumberFormatException - if in is not a valid representation of a BigDecimal or the
defined subarray is not wholly within in .

<press tab to see next documentation>

jshell> new BigDecimal(
BigDecimal(char[] in)
Translates a character array representation of a BigDecimal into a BigDecimal ,
accepting the same sequence of characters as the #BigDecimal(String) constructor.

Parameters:
in - char array that is the source of characters.

Thrown Exceptions:
NumberFormatException - if in is not a valid representation of a BigDecimal .

<press tab to see next documentation>

jshell> new BigDecimal(
BigDecimal(char[] in, MathContext mc)
Translates a character array representation of a BigDecimal into a BigDecimal ,
accepting the same sequence of characters as the #BigDecimal(String) constructor and
with rounding according to the context settings.

Parameters:
in - char array that is the source of characters.
mc - the context to use.

Thrown Exceptions:
ArithmeticException - if the result is inexact but the rounding mode is UNNECESSARY
.
NumberFormatException - if in is not a valid representation of a BigDecimal .

<press tab to see next documentation>

jshell> new BigDecimal(    
$1        $12       $13       $14       $15       $16       $17       $18       
$2        $3        $33       $4        $5        $51       $52       $57       
$58       $59       $6        $63       $7        $8        $9        big       
c         dbl       eight     f         f2        fifteen   i         i1        
j         l         s         sixteen

Signatures:
BigDecimal(char[] in, int offset, int len)
BigDecimal(char[] in, int offset, int len, MathContext mc)
BigDecimal(char[] in)
BigDecimal(char[] in, MathContext mc)
BigDecimal(String val)
BigDecimal(String val, MathContext mc)
BigDecimal(double val)
BigDecimal(double val, MathContext mc)
BigDecimal(BigInteger val)
BigDecimal(BigInteger val, MathContext mc)
BigDecimal(BigInteger unscaledVal, int scale)
BigDecimal(BigInteger unscaledVal, int scale, MathContext mc)
BigDecimal(int val)
BigDecimal(int val, MathContext mc)
BigDecimal(long val)
BigDecimal(long val, MathContext mc)

<press tab again to see documentation>

jshell> clear()


jshell> number1.
abs(                     add(                     byteValue()              
byteValueExact()         compareTo(               divide(                  
divideAndRemainder(      divideToIntegralValue(   doubleValue()            
equals(                  floatValue()             getClass()               
hashCode()               intValue()               intValueExact()          
longValue()              longValueExact()         max(                     
min(                     movePointLeft(           movePointRight(          
multiply(                negate(                  notify()                 
notifyAll()              plus(                    pow(                     
precision()              remainder(               round(                   
scale()                  scaleByPowerOfTen(       setScale(                
shortValue()             shortValueExact()        signum()                 
sqrt(                    stripTrailingZeros()     subtract(                
toBigInteger()           toBigIntegerExact()      toEngineeringString()    
toPlainString()          toString()               ulp()                    
unscaledValue()          wait(

jshell> clear()



jshell> boolean isValue = true
isValue ==> true

jshell> boolean isValue = false
isValue ==> false

jshell> boolean isValid = false
isValid ==> false

jshell> boolean isValid = False
|  Error:
|  cannot find symbol
|    symbol:   variable False
|  boolean isValid = False;
|                    ^---^

jshell> boolean isValid = FALSE
|  Error:
|  cannot find symbol
|    symbol:   variable FALSE
|  boolean isValid = FALSE;
|                    ^---^

jshell> int i = 7;
i ==> 7

jshell> i > 7
$99 ==> false

jshell> i >= 7
$100 ==> true

jshell> i < 7
$101 ==> false

jshell> i <= 7
$102 ==> true

jshell> i == 6
$103 ==> false

jshell> i == 7
$104 ==> true

jshell> i == 8
$105 ==> false

jshell> i = 8
i ==> 8

jshell> i = 7
i ==> 7

jshell> i == 7
$108 ==> true

jshell> clear()


jshell> int i = 10
i ==> 10

jshell> i > 15
$111 ==> false

jshell> clear()















jshell> int i = 17
i ==> 17

jshell> i >= 15
$114 ==> true

jshell> i <= 25
$115 ==> true

jshell> i >= 15 && i <= 25
$116 ==> true

jshell> i = 30
i ==> 30

jshell> i >= 15 && i <= 25
$118 ==> false

jshell> i = 5
i ==> 5

jshell> i >= 15 && i <= 25
$120 ==> false

jshell> true && true
$121 ==> true

jshell> true && false
$122 ==> false

jshell> false && true
$123 ==> false

jshell> false && false
$124 ==> false

jshell> false || true
$126 ==> true

jshell> true || false
$127 ==> true

jshell> true || true
$128 ==> true

jshell> false || false
$129 ==> false

jshell> false ^ false
$130 ==> false

jshell> false ^ true
$131 ==> true

jshell> true ^ false
$132 ==> true

jshell> true ^ true
$133 ==> false

jshell> !true
$134 ==> false

jshell> !false
$135 ==> true

jshell> !x>7
|  Error:
|  cannot find symbol
|    symbol:   variable x
|  !x>7
|   ^

jshell> int x = 6
x ==> 6

jshell> !(x>7)
$138 ==> true

jshell> clear()




jshell> int i = 10
i ==> 10

jshell> int j = 15
j ==> 15

jshell> j > 15 && i++ > 5
$145 ==> false

jshell> j
j ==> 15

jshell> i
i ==> 10

jshell> j > 15 & i++ > 5
$148 ==> false

jshell> j
j ==> 15

jshell> i
i ==> 11

jshell> i++;
$151 ==> 11

jshell> i++;
$152 ==> 12

jshell> clear()


jshell> char ch = a
|  Error:
|  cannot find symbol
|    symbol:   variable a
|  char ch = a;
|            ^

jshell> char ch = 'a'
ch ==> 'a'

jshell> char ch = 'ab'
|  Error:
|  unclosed character literal
|  char ch = 'ab'
|            ^

jshell> char ch = 'a'
ch ==> 'a'

jshell> char ch2 = '\u0022'
ch2 ==> '"'

jshell> char ch3 = '\u00A2'
ch3 ==> '¢'

jshell> char ch = 65
ch ==> 'A'

jshell> ch++
$159 ==> 'A'

jshell> ch
ch ==> 'B'

jshell> ++ch
$161 ==> 'C'

jshell> ++ch
$162 ==> 'D'

jshell> ch + 5
$163 ==> 73

jshell> ch
ch ==> 'D'

jshell> (int)ch
$165 ==> 68

jshell> char ch = '\n'
ch ==> '\n'

jshell> ch
ch ==> '\n'

jshell> System.out.println(ch);



jshell> char ch = '/t'
|  Error:
|  unclosed character literal
|  char ch = '/t'
|            ^

jshell> char ch = '\t'
ch ==> '\t'

jshell> System.out.println(ch);


jshell> System.out.println(ch);


jshell> (int)'1'
$172 ==> 49

jshell> (int)'0'
$173 ==> 48

jshell> (int)'9'
$174 ==> 57

jshell> (int)'2'
$175 ==> 50

jshell> (int)'a'
$176 ==> 97

jshell> (int)'z'
$177 ==> 122

jshell> (int)'A'
$178 ==> 65

jshell> (int)'Z'
$179 ==> 90

jshell>         for (char ch = 'A'; ch <= 'Z'; ch++) {
...>             System.out.println(ch);
...>
...> }
A
B
C
D
E
F
G
H
I
J
K
L
M
N
O
P
Q
R
S
T
U
V
W
X
Y
Z

jshell>   for (char ch = 'A'; ch <= 'Z'; ch++) {
...>             System.out.println(ch);
...> }
A
B
C
D
E
F
G
H
I
J
K
L
M
N
O
P
Q
R
S
T
U
V
W
X
Y
Z

jshell>   for (char ch = 'A'; ch <= 'C'; ch++) {
...>             System.out.println(ch);
...> }
A
B
C

jshell> }

# Loops:
clear()
for(i=1;i<=10;i++) {
if(i==5)
break;
System.out.print(i + " ");
}
for(i=1;i<=10;i++) {
if(i%2==0)
break;
System.out.print(i + " ");
}
for(i=1;i<=10;i++) {
if(i%2==0)
continue;
System.out.print(i + " ");
}
for(i=1;i<=10;i++) {
if(i%2!=0)
continue;
System.out.print(i + " ");
}

# References
Last login: Wed Jan 31 17:39:19 on ttys002
Rangas-MacBook-Pro:~ rangaraokaranam$ "Test".length()
>
Rangas-MacBook-Pro:~ rangaraokaranam$ jshell
|  Welcome to JShell -- Version 9.0.1
|  For an introduction type: /help intro

jshell> "Test".length()
$1 ==> 4

jshell> String str = "Test"
str ==> "Test"

jshell> BigDecimal bd = new BigDecimal("1.0");
bd ==> 1.0

jshell> String str = new String("Test")
str ==> "Test"

jshell> String str = "Test"
str ==> "Test"

jshell> str.charAt(0)
$6 ==> 'T'

jshell> str.charAt(2)
$7 ==> 's'

jshell> str.charAt(3)
$8 ==> 't'

jshell> String biggerString = "This is a lot of text"
biggerString ==> "This is a lot of text"

jshell> biggerString.substring(5)
$11 ==> "is a lot of text"

jshell> biggerString.substring(5,13)
$12 ==> "is a lot"

jshell> str.charAt(13)
|  java.lang.StringIndexOutOfBoundsException thrown: String index out of range: 13
|        at StringLatin1.charAt (StringLatin1.java:44)
|        at String.charAt (String.java:704)
|        at (#13:1)

jshell> biggerString.charAt(13)
$14 ==> ' '

jshell> biggerString.charAt(456)
|  java.lang.StringIndexOutOfBoundsException thrown: String index out of range: 456
|        at StringLatin1.charAt (StringLatin1.java:44)
|        at String.charAt (String.java:704)
|        at (#15:1)

jshell> String someString = "This is a lot of text again"
someString ==> "This is a lot of text again"

jshell> clear()
|  Error:
|  cannot find symbol
|    symbol:   method clear()
|  clear()
|  ^---^

jshell> void clear() { System.out.print("\033[H\033[2J ");}
|  created method clear()

jshell> clear()


jshell> String someString = "This is a lot of text again"
someString ==> "This is a lot of text again"

jshell> someString.length()
$20 ==> 27

jshell> someString.charAt(5)
$21 ==> 'i'

jshell> for(int i= 0; i<someString.length(); i++) {
...>     System.out.println(someString.charAt(i));
...> }
T
h
i
s

i
s

a

l
o
t

o
f

t
e
x
t

a
g
a
i
n

jshell> clear()


jshell> String someString = "This is a lot of text again"
someString ==> "This is a lot of text again"

jshell> someString.indexOf("lot")
$25 ==> 10

jshell> someString.charAt(10)
$26 ==> 'l'

jshell> someString.charAt('i')
|  java.lang.StringIndexOutOfBoundsException thrown: String index out of range: 105
|        at StringLatin1.charAt (StringLatin1.java:44)
|        at String.charAt (String.java:704)
|        at (#27:1)

jshell> someString.indexOf('i')
$28 ==> 2

jshell> someString.lastIndexOf('i')
$29 ==> 25

jshell> someString.contains("text")
$30 ==> true

jshell> String someString = "This is a lot of text again"
someString ==> "This is a lot of text again"

jshell> clear()


jshell> String someString = "This is a lot of text again"
someString ==> "This is a lot of text again"

jshell> someString.startsWith("This")
$34 ==> true

jshell> someString.startsWith("jfsdklfj")
$35 ==> false

jshell> someString.endsWith("jfsdklfj")
$36 ==> false

jshell> someString.endsWith("in")
$37 ==> true

jshell> someString.endsWith("ain")
$38 ==> true

jshell> someString.endsWith("again")
$39 ==> true

jshell> someString.endsWith("againfsda")
$40 ==> false

jshell> someString.empty()
|  Error:
|  cannot find symbol
|    symbol:   method empty()
|  someString.empty()
|  ^--------------^

jshell> someString.isEmpty()
$41 ==> false

jshell> "fjsadlkfj".isEmpty()
$42 ==> false

jshell> "".isEmpty()
$43 ==> true

jshell> "true".equals("true")
$44 ==> true

jshell> "value".equals("value")
$45 ==> true

jshell> Str str = "value"
|  Error:
|  cannot find symbol
|    symbol:   class Str
|  Str str = "value";
|  ^-^

jshell> String str = "value"
str ==> "value"

jshell> str.equals("value")
$47 ==> true

jshell> str.equals("VAlue")
$48 ==> false

jshell> str.equalsIgnoreCase("VAlue")
$49 ==> true

jshell> clear()


jshell> String str = "in28Minutes"
str ==> "in28Minutes"

jshell> str.concat("is awesome");
$52 ==> "in28Minutesis awesome"

jshell> str
str ==> "in28Minutes"

jshell> String anotherString = str.concat(" is awesome");
anotherString ==> "in28Minutes is awesome"

jshell> str
str ==> "in28Minutes"

jshell> anotherString
anotherString ==> "in28Minutes is awesome"

jshell> String string2 = anotherString.concat(".");
string2 ==> "in28Minutes is awesome."

jshell> str
str ==> "in28Minutes"

jshell> anotherString
anotherString ==> "in28Minutes is awesome"

jshell> string2
string2 ==> "in28Minutes is awesome."

jshell> clear()


jshell> String str = "in28Minutes is awesome."
str ==> "in28Minutes is awesome."

jshell> str.toUpperCase()
$63 ==> "IN28MINUTES IS AWESOME."

jshell> str.toLowerCase()
$64 ==> "in28minutes is awesome."

jshell> String str2 = "  in28Minutes is awesome.   "
str2 ==> "  in28Minutes is awesome.   "

jshell> str2.trim()
$68 ==> "in28Minutes is awesome."

jshell> clear()






jshell> 1 + 2
$70 ==> 3

jshell> "1" + "2"
$71 ==> "12"

jshell> "1" + 2
$72 ==> "12"

jshell> "1" + 23
$73 ==> "123"

jshell> 1 + 23
$74 ==> 24

jshell> 1 + 2 + "3"
$75 ==> "33"

jshell> "1" + 2 + 3
$76 ==> "123"

jshell> int i = 20;
i ==> 20

jshell> System.out.println("Value is " + 20)
Value is 20

jshell> System.out.println("Value is " + 20 + 20)
Value is 2020

jshell> System.out.println("Value is " + (20 + 20))
Value is 40

jshell> String.join(",", "2", "3", "4")
$81 ==> "2,3,4"

jshell> String.join(",", "A", "B","C")
$82 ==> "A,B,C"

jshell> String.join(",", "A")
$83 ==> "A"

jshell> String.join(",", "A", "B")
$84 ==> "A,B"

jshell> "abcd".replace('a', 'z');
$85 ==> "zbcd"

jshell> "abcd".replace("ab", "xyz");
$86 ==> "xyzcd"

jshell> String str = "jdsfja "
str ==> "jdsfja "

jshell> str.
charAt(                chars()                codePointAt(           
codePointBefore(       codePointCount(        codePoints()           
compareTo(             compareToIgnoreCase(   concat(                
contains(              contentEquals(         endsWith(              
equals(                equalsIgnoreCase(      getBytes(              
getChars(              getClass()             hashCode()             
indexOf(               intern()               isEmpty()              
lastIndexOf(           length()               matches(               
notify()               notifyAll()            offsetByCodePoints(    
regionMatches(         replace(               replaceAll(            
replaceFirst(          split(                 startsWith(            
subSequence(           substring(             toCharArray()          
toLowerCase(           toString()             toUpperCase(           
trim()                 wait(

jshell> String.
CASE_INSENSITIVE_ORDER   class                    copyValueOf(             
format(                  join(                    valueOf(

jshell> clear()


jshell> "123" + "123" + "1234" + "123456"
$89 ==> "1231231234123456"

jshell> StringBuffer sb = "TEst";
|  Error:
|  incompatible types: java.lang.String cannot be converted to java.lang.StringBuffer
|  StringBuffer sb = "TEst";
|                    ^----^

jshell> StringBuffer sb = new StringBuffer("TEst");
sb ==> TEst

jshell> sb.append
append(            appendCodePoint(

jshell> sb.append(" 123")
$91 ==> TEst 123

jshell> sb
sb ==> TEst 123

jshell> sb.setCharAt(1,'e')

jshell> sb
sb ==> Test 123

jshell> StringBuilder sb = new StringBuilder("test");
sb ==> test

jshell> clear()








jshell> int i = 5
i ==> 5

jshell> Integer integer = new Integer(5);
integer ==> 5

jshell> Integer integer1 = new Integer(5);
integer1 ==> 5

jshell> Integer integer = Integer.valueOf(5);
integer ==> 5

jshell> Integer integer1 = Integer.valueOf(5);
integer1 ==> 5

jshell> Integer integer = new Integer("5234");
integer ==> 5234

jshell> Integer integer1 = new Integer("5234");
integer1 ==> 5234

jshell> Integer integer2 = new Integer("5234");
integer2 ==> 5234

jshell> clear()


jshell> Integer i1 = new Integer(5)
i1 ==> 5

jshell> Integer i2 = new Integer(5)
i2 ==> 5

jshell> Integer i3 = Integer.valueOf(5)
i3 ==> 5

jshell> Integer i4 = Integer.valueOf(5)
i4 ==> 5

jshell> i1 == i2
$111 ==> false

jshell> i3 == i4
$112 ==> true

jshell> clear()



jshell> Integer integer = Integer.valueOf("4567");
integer ==> 4567

jshell> int i = integer.intValue();
i ==> 4567

jshell> Float f = Float.valueOf("12.45")
f ==> 12.45

jshell> f.floatValue()
$117 ==> 12.45

jshell> f.intValue()
$118 ==> 12

jshell> Integer eight = Integer.valueOf(8);
eight ==> 8

jshell> Integer.toBinaryString(
toBinaryString(

jshell> Integer.toBinaryString(eight);
$120 ==> "1000"

jshell> Integer.toHexString(eight);
$121 ==> "8"

jshell> Integer eightyEight = Integer.valueOf(88);
eightyEight ==> 88

jshell> Integer.toHexString(eightyEight);
$123 ==> "58"

jshell> clear()


jshell> Integer seven = Integer.valueOf(7);
seven ==> 7

jshell> Integer seven = 7;
seven ==> 7

jshell> Integer sevenAgain = 7;
sevenAgain ==> 7

jshell> seven == sevenAgain
$133 ==> true

jshell> Integer.MAX_VALUE
$134 ==> 2147483647

jshell> Integer.MIN_VALUE
$135 ==> -2147483648

jshell> Integer.SIZE
$136 ==> 32

jshell> Integer.BYTES
$137 ==> 4

jshell> clear()


jshell> LocalDate now = LocalDate.now()
|  Error:
|  cannot find symbol
|    symbol:   class LocalDate
|  LocalDate now = LocalDate.now();
|  ^-------^
|  Error:
|  cannot find symbol
|    symbol:   variable LocalDate
|  LocalDate now = LocalDate.now();
|                  ^-------^

jshell> /imports
|    import java.io.*
|    import java.math.*
|    import java.net.*
|    import java.nio.file.*
|    import java.util.*
|    import java.util.concurrent.*
|    import java.util.function.*
|    import java.util.prefs.*
|    import java.util.regex.*
|    import java.util.stream.*

jshell> import java.time.LocalDate

jshell> LocalDate now = LocalDate.now()
now ==> 2018-02-01

jshell> LocalDateTime now = LocalDateTime.now()
|  Error:
|  cannot find symbol
|    symbol:   class LocalDateTime
|  LocalDateTime now = LocalDateTime.now();
|  ^-----------^
|  Error:
|  cannot find symbol
|    symbol:   variable LocalDateTime
|  LocalDateTime now = LocalDateTime.now();
|                      ^-----------^

jshell> import java.time.LocalDateTime

jshell> import java.time.*

jshell> LocalDateTime now = LocalDateTime.now()
now ==> 2018-02-01T15:50:48.423164

jshell> LocalDate now = LocalDate.now()
now ==> 2018-02-01

jshell> LocalTime now = LocalTime.now()
now ==> 15:51:09.642001

jshell> clear()


jshell> LocalDate today = LocalDate.now()
today ==> 2018-02-01

jshell> import java.time.*

jshell> today.getYear()
getYear()

jshell> today.getYear()
$149 ==> 2018

jshell> today.getDayOf()
getDayOfMonth()   getDayOfWeek()    getDayOfYear()

jshell> today.getDayOfWeek()
$150 ==> THURSDAY

jshell> today.getDayOfMonth()
$151 ==> 1

jshell> today.getDayOfYear()
$152 ==> 32

jshell> today.getMonth()
$153 ==> FEBRUARY

jshell> today.getMonthValue()
$154 ==> 2

jshell> today.isLeapYear()
isLeapYear()

jshell> today.isLeapYear()
$155 ==> false

jshell> today.lengthOfYear()
$156 ==> 365

jshell> today.lengthOfMonth()
$157 ==> 28

jshell> today.plusDays(100)
$158 ==> 2018-05-12

jshell> today.plusMonths(100)
$159 ==> 2026-06-01

jshell> today.plusYears(100)
$160 ==> 2118-02-01

jshell> today.minusYears(100)
$161 ==> 1918-02-01

jshell> LocalDate hundredYearsBefore = today.minusYears(100)
hundredYearsBefore ==> 1918-02-01

jshell> today
today ==> 2018-02-01

jshell> LocalDateTime now = LocalDateTime.now()
now ==> 2018-02-01T16:03:37.567117

jshell> now.plus
plus(          plusDays(      plusHours(     plusMinutes(   plusMonths(    
plusNanos(     plusSeconds(   plusWeeks(     plusYears(

jshell> now.get
get(              getChronology()   getClass()        getDayOfMonth()   
getDayOfWeek()    getDayOfYear()    getHour()         getLong(          
getMinute()       getMonth()        getMonthValue()   getNano()         
getSecond()       getYear()

jshell> clear()


jshell> LocalDate today = LocalDate.now()
today ==> 2018-02-01

jshell> LocalDate yesterady = LocalDate.of(2018, 01. 31)
|  Error:
|  ')' expected
|  LocalDate yesterady = LocalDate.of(2018, 01. 31);
|                                              ^

jshell> LocalDate yesterady = LocalDate.of(2018, 01, 31)
yesterady ==> 2018-01-31

jshell> LocalDate yesterday = LocalDate.of(2018, 01, 31)
yesterday ==> 2018-01-31

jshell> today
today ==> 2018-02-01

jshell> yesterday
yesterday ==> 2018-01-31

jshell> today.withYear(2016)
$171 ==> 2016-02-01

jshell> today.withDayOfMonth(20)
$172 ==> 2018-02-20

jshell> today.withMonth(3)
$173 ==> 2018-03-01

jshell> today.withDayOfYear(120)
$174 ==> 2018-04-30

jshell> today.isBefore(yesterday)
$175 ==> false

jshell> today.isAfter(yesterday)
$176 ==> true

jshell> /save /in28Minutes/git/java-a-course-for-beginners/9-ReferenceTypes/commands.txt

jshell> 

