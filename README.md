Quote calculator.

Building
```
./gradlew shadowJar
```

Running
```
cd build/libs/
java -jar rate-calculation-all.jar [path] [amount]
```

Example
```
java -jar rate-calculation-all.jar ../../src/test/resources/market.csv 1000
Requested amount: £1000
Rate: 7.0%
Monthly repayment: £30.88
Total repayment: £1111.64
```