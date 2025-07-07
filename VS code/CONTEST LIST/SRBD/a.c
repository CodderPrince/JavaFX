/******************************************************
 *    Author:  PRINCE_14                              *
 *          --MD. AN NAHIAN PRINCE                    *
 *          --CSE,BRUR                                *
 *            PB: A. To My Critics                    *
 ******************************************************/

#include <stdio.h>

int main() {
    int t;
    scanf("%d", &t);
    
    while (t--) {
        int budget, cost_per_problem;
        scanf("%d %d", &budget, &cost_per_problem);
        
        int total_problems = 0;
        
        // Calculate the maximum number of problems for the first round
        int max_first_round_problems = budget / (2 * cost_per_problem);
        
        // Try different numbers of problems for the first round
        for (int first_round_problems = 0; first_round_problems <= max_first_round_problems; first_round_problems++) {
            int remaining_budget = budget - (2 * first_round_problems * cost_per_problem);
            int second_round_problems = remaining_budget / cost_per_problem;
            int current_participants = first_round_problems + second_round_problems;
            
            if (current_participants >= total_problems) {
                total_problems = current_participants;
            }
        }
        
        printf("%d\n", total_problems);
    }
    
    return 0;
}
