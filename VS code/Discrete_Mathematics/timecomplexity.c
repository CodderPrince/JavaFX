/*BASIC TIPS ABOUT TIME COMPLEXITY
1. JODI LINEAR SEARCH ER MOTO SIMPLE CODE HOI JEKHANE INPUT JAI HOK NA KENO RESULT E PROBLEM
HOBE NA TAHOLE O(1); exm. sum er jonno
2. jodi 2 kore multiple or 2 kore divide hoi tahole log2 (n)
joto diye multiple or divide hobe toto log er base
3. jodi condition i*i<=n emon thake tahole kheyal rakhte hobe eta sqrt er formula
4. output jodi input er upor nirvor kore tahole O(n) hobe ar jodi 1 er besi hoi tahole multiple
hote thakbe jemon n*n*n = n^3 emon hote thakbe 
5. jodi factorial er type thake tahole setao kheyal rakhte hobe

*/

//1 O(1)
/*
#include<stdio.h>
int main()
{
    int a=5;
    int b=50;
    int c=a+b;
    jehetu ekhane fixed ekta calculation bade ar kono kaj korte parbe na tai 
    time complexity: big o notation of 1
}
*/
/*The code you provided has a time complexity of O(1), which means that the time 
it takes to execute the code is constant and does not depend on the size of the input. 
This is because the code consists of a fixed set of operations (assigning values to 
variables and adding them) that will always take the same amount of time to execute, 
regardless of the values of the variables. The values of the variables are known at 
compile-time, so the compiler can optimize the code and generate machine code that 
performs the addition operation directly without any loops or conditional statements.*/

//2 O(log n)
/*
#include<stdio.h>
int main()
{
    int i=1;
    while(i<n)
    {
        printf("%d",i);
        i= i*2;
    }
}
time complexity?


The time complexity of the given code is O(log n), where n is the value of the variable 
that is being used as the loop condition.
The code uses a while loop that continues to execute as long as the value of the loop 
variable `i` is less than `n`. Inside the loop, the code prints the value of `i` and 
then multiplies it by 2, effectively doubling its value. This means that the loop will 
execute approximately log base 2 of `n` times, since `i` is being doubled at each 
iteration.
Therefore, the time complexity of the code is O(log n), which is a more efficient time
complexity than O(n) for large values of `n`.
*/

//3  O(n)
/*
#include<stdio.h>
int main()
{
    for(int i=1; i<=n; i++)
    loop ta n times cholbe ar n holo input number 
    {
        sum= sum+i;
    }
}
time complexity?

The time complexity of the given code is O(n), where n is the value of the variable used 
in the loop condition.
The code uses a for loop that executes `n` times, where `n` is the value of the variable
used as the loop condition. Inside the loop, the code adds 
the value of `i` to the variable `sum`. Since the loop executes `n` times, the number of 
additions performed is also `n`.
Therefore, the time complexity of the code is O(n), which means that the time required 
to execute the code will increase linearly with the size of the input `n`.
*/

//4  O(sqrt(n))
/*
#include<stdio.h>
int main()
{
    int sum=0;
    for(int i=1; sum<=n; i++)
    {
        sum= sum+i;
    }
}
time complexity?

The time complexity of the given code is O(sqrt(n)), where n is the value of the variable
used in the loop condition.
The code uses a for loop that executes until the value of `sum` exceeds `n`. Inside the 
loop, the code adds the value of `i` to the variable `sum`. 
The loop will execute approximately sqrt(2n) times, since the sum of the first `k` positive 
integers is given by k(k+1)/2, and we want to find the largest `k` such that k(k+1)/2 <= n. 
We can approximate this value of `k` by solving for `k` in the equation k(k+1)/2 = n, which 
yields k^2 + k - 2n = 0, and then taking the square root and rounding down to the nearest 
integer.
Therefore, the time complexity of the code is O(sqrt(n)), which means that the time required to execute the code will increase proportionally to the square root of the input `n`.
*/

//5  O(n^2)
/*
#include<stdio.h>
int main()
{
    int n=5;
    for(int i=1; i<=n; i++)
    {
        for(int j=1; j<=n; j++)
        {
            printf("%d,%d",i,j);
        }
        printf("\n");
    }
}
time complexity?

The time complexity of the given code is O(n^2), where n is the value of the variable used 
in the outer loop.
The code uses nested for loops to print out all pairs of integers from 1 to `n`. The inner 
loop executes `n` times for each iteration of the outer loop, 
resulting in a total of `n^2` iterations of the inner loop.
Therefore, the time complexity of the code is O(n^2), which means that the time required to 
execute the code will increase quadratically with the size of the input `n`.
*/


//6  O(n)
/*
#include<stdio.h>
int main()
{
    int sum(int n)
    {
        int result=0;
        int i = 1;
        while(i<=n)
        {
            result= result+i;
            i++;
        }
        return result;
    }
}
time complexity?

The time complexity of the given code is O(n), where `n` is the input to the `sum` function.
The function uses a while loop that executes `n` times (from 1 to `n`) and adds the value of 
`i` to a running total. The loop body contains a fixed set of operations 
that execute in constant time, so the time required to execute the loop is proportional to 
the number of iterations.
Therefore, the time complexity of the `sum` function is O(n), which means that the time 
required to execute the function will increase linearly with the size of the input `n`.
*/

//7 O(n)
/*
#include<stdio.h>
int main()
{
   int product(int n)
   {
    if(n==0)
    {
        return 1;
    }
    else
    {
        return n*product(n-1);
    }
   }                                                   
}
time complexity?

The time complexity of the given code is O(n), where `n` is the input to the `product` 
function.
The function uses recursion to calculate the product of the first `n` positive integers. 
The base case of the recursion is when `n` is 0, in which case the function returns 1. 
Otherwise, the function multiplies `n` by the result of calling `product(n-1)`, which means 
that the function will recursively call itself `n` times.
The time complexity of the function can be expressed using the recurrence relation 
T(n) = T(n-1) + O(1), where T(n) is the time required to calculate the product of the 
first `n` positive integers. The base case is T(0) = O(1), since it only requires one 
operation. Using the recurrence relation and the base case, we can derive that T(n) = O(n), 
since there are `n` recursive calls, and each call requires constant time to execute.
Therefore, the time complexity of the `product` function is O(n), which means that the time 
required to execute the function will increase linearly with the size of the input `n`.
*/

//8 O(n)
/*
#include<stdio.h>
    int factorial(int n)
    {
        int result =1;
        for(int i=1; i<=n; i++)
        {
            resutl= result*i;
        }
        return result;
    }
time complexity?

The time complexity of the `factorial` function you provided is O(n), where n is the 
input parameter to the function. 
This is because the function contains a single loop that iterates n times, performing a 
constant amount of work (i.e., multiplying the current value of `result` by `i`) on each 
iteration. Therefore, the total amount of work performed by the loop is proportional to n, 
resulting in a time complexity of O(n).
Note that the time complexity of calling the `factorial` function would depend on the number 
of times it is called and the values passed as arguments.
*/

//9 O(n log log n)
/*
#include<stdio.h>
    int primes(n)
    {
        for(int i=2; i<n; i++)
        {
            primes[i]=1;
        }
        for(int p=2; p*p<n; p++)
        {
            if(primes[p])
            {
                for(int i=p*p; i<n; i=i+p)
                {
                    primes[i]=0;
                }
            }
        }
    }
time complexity?

The time complexity of the `primes` function you provided is O(n log log n), where n is the 
input parameter to the function.
This is because the function implements the Sieve of Eratosthenes algorithm to find all prime 
numbers up to n. The first loop that initializes the `primes` array is O(n), since it executes 
n times and performs a constant amount of work on each iteration.
The second loop that implements the Sieve of Eratosthenes algorithm has an outer loop that 
executes sqrt(n) times and an inner loop that executes n/p times for each prime number p up 
to sqrt(n). The total number of iterations of the inner loop is approximately 
n/2 + n/3 + n/5 + n/7 + ..., which is bounded by the sum of the reciprocals of the 
primes less than or equal to n, which is log log n. Therefore, the time complexity of 
the second loop is O(n log log n).
Combining the time complexity of the two loops, we get a total time complexity of O(n log log n) for the `primes` function.
*/

///10 O(n^3 log n)
/*
#include<stdio.h>
    int main()
    {
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                for(int k=0; k<n; k*2)
                {
                    printf("%d %d %d\n",i,j,k);
                }
            }
        }
    
    }
time complexity?
The time complexity of the nested loops in the `main` function you provided is O(n^3 log n), 
where n is the upper bound of the loops.
The innermost loop increments the variable `k` exponentially by multiplying it by 2, so 
it will execute log n times before `k` exceeds the value of `n`. The middle loop executes 
`n` times, and the innermost loop executes log n times for each iteration of the middle loop. 
Therefore, the total number of iterations of the innermost loop is O(n log n).
The outermost loop also executes `n` times, and for each iteration, the middle loop and the 
innermost loop execute O(n log n) times. Multiplying these together gives a total time 
complexity of O(n^3 log n) for the nested loops.
Note that the time complexity of the entire `main` function is not well-defined in this case, 
since it does not perform any significant work apart from the nested loops.
*/

//11 O(n log n)
/*
#include<stdio.h>
    int main()
    {
        for(int i=0; i<n; i++)
        {
            int j=n;
            while(j>1)
            {
                j=j/2;
            }
        }
    
    }
time complexity?
The time complexity of the nested loops in the `main` function you provided is O(n log n), 
where n is the upper bound of the loops.
The outer loop executes `n` times, and for each iteration, the inner loop executes log n times. 
This is because the inner loop repeatedly divides the variable `j` by 2 until it reaches a 
value of 1 or less. Since `j` starts at `n`, it will take log base 2 of `n` iterations to 
reach a value of 1. Therefore, the total number of iterations of the inner loop is O(log n).
Multiplying the number of iterations of the outer loop (`n`) with the number of iterations 
of the inner loop (`log n`) gives a total time complexity of O(n log n) for the nested loops.
Note that the time complexity of the entire `main` function is not well-defined in this case, 
since it does not perform any significant work apart from the nested loops.
*/

//12 O(n^2 log n)
/*
#include<stdio.h>
    int main()
    {
        for(int i=1; i<n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                statements;
            }
        }
        for(int k=1; k<=n; k=k*2)
        {
            printf("%d %d %d\n",i,j,k);
        }
    
    }
time complexity?
The time complexity of the nested loops and the subsequent `printf` statement in the `main` 
function you provided is O(n^2 log n), where n is the upper bound of the loops.
The first two nested loops execute `n^2` times, since the outer loop executes `n-1` times 
and the inner loop executes `n` times. The statements inside the inner loop are assumed to 
have a constant time complexity, so the total time complexity of the first two loops combined 
is O(n^2).
The third loop executes log n times, since the variable `k` doubles on each iteration until 
it exceeds `n`. Therefore, the total time complexity of the third loop is O(log n).
The `printf` statement inside the third loop has a constant time complexity, so the total 
time complexity of the `printf` statements is O(log n).
Multiplying the time complexity of the first two loops (`O(n^2)`) with the time complexity 
of the third loop and the `printf` statements (`O(log n)`) gives a total time complexity of 
O(n^2 log n) for the `main` function.
Note that the variables `i` and `j` used in the `printf` statement are not defined in the 
scope of the third loop, so the code may not compile.
*/

//13 O(n sqrt(n))
/*
#include<stdio.h>
    int main()
    {
        for(int i=0; i<n; i++)
        for(int i=0; i<n; i++)
        for(int i=2; i*i<=n; i++)
        {
            if(n%i==0)
            {
                printf("%d\n",i);
            }
        }
    
    }
time complexity?
The time complexity of the nested loops and the subsequent `printf` statement in the 
`main` function you provided is O(n sqrt(n)), where n is the input parameter to the loops.
The first two nested loops execute `n^2` times, since each loop executes `n` times. 
The third loop performs trial division on the input `n` by checking all numbers `i` 
from 2 up to the square root of `n`. Therefore, the total number of iterations of the 
third loop is sqrt(n).
Inside the third loop, the conditional statement performs a constant amount of work 
(i.e., checking whether `n` is divisible by `i`) on each iteration. Therefore, the total 
amount of work performed by the third loop is proportional to sqrt(n).
Multiplying the time complexities of the three loops together, we get a total time 
complexity of O(n sqrt(n)) for the `main` function.
Note that the variables used in the second loop are declared with the same name `i` 
as the variables used in the first loop, which may cause a compilation error.
*/

//14  O(n + sqrt(n))
/*
#include<stdio.h>
    int main()
    {
        for(int i=0; i<n; i++)
        statements;
        for(int i=2; i*i<=n; i++)
        {
            if(n%i==0)
            {
                printf("%d\n",i);
            }
        }
    }
time complexity?
The time complexity of the two loops and the subsequent `printf` statement in the `main` 
function you provided depends on the time complexity of the `statements` inside the first 
loop. Without knowing the time complexity of those statements, it is not possible to determine 
the overall time complexity of the program.
Assuming that the `statements` inside the first loop have a constant time complexity, the 
time complexity of the program would be O(n + sqrt(n)), where n is the input parameter to 
the loops. The first loop executes `n` times, and the second loop performs trial division 
on the input `n` by checking all numbers `i` from 2 up to the square root of `n`, which has 
a time complexity of O(sqrt(n)). 
Inside the second loop, the conditional statement performs a constant amount of work 
(i.e., checking whether `n` is divisible by `i`) on each iteration. Therefore, the total 
amount of work performed by the second loop is proportional to sqrt(n).
Since the second loop dominates the time complexity of the program, the overall time 
complexity is O(n + sqrt(n)).
Note that the variable `i` used in the second loop has the same name as the variable 
used in the first loop, which may cause a compilation error. It is recommended to use 
different variable names to avoid confusion.
*/

//15  O(n sqrt(n) log n)
/*
#include<stdio.h>
    int main()
    {
        for(int i=0; i<n; i++)
        statements;
        for(int i=1; i*i<=n; i++)
        for(int i=1; i<=n; i*2)
        {
            if(n%i==0)
            {
                printf("%d\n",i);
            }
        }
    }
time complexity?
The time complexity of the nested loops and the subsequent `printf` statement in the `main` 
function you provided is O(n sqrt(n) log n), where n is the input parameter to the loops.
The first loop executes `n` times, and the statements inside the loop are assumed to have 
a constant time complexity. Therefore, the total time complexity of the first loop is O(n).
The second loop performs trial division on the input `n` by checking all numbers `i` from 
1 up to the square root of `n`. Therefore, the total number of iterations of the second loop 
is sqrt(n).
The third loop doubles the value of `i` on each iteration until it exceeds `n`. Since the 
initial value of `i` is 1, the third loop executes log n times.
Inside the nested loops, the conditional statement performs a constant amount of work 
(i.e., checking whether `n` is divisible by `i`) on each iteration. Therefore, the total 
amount of work performed by the nested loops is proportional to sqrt(n) log n.
Multiplying the time complexities of the three loops together, we get a total time complexity 
of O(n sqrt(n) log n) for the `main` function.
Note that the variables used in the second and third loops have the same name `i`, which may 
cause a compilation error. It is recommended to use different variable names to avoid confusion.
*/