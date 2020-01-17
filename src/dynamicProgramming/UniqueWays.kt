/*
 * Copyright 2019 Abdelrhman Sror. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 */

@file:Suppress("KDocUnresolvedReference")

package dynamicProgramming

fun main() {
    print(
        calNumberOfPermutationUsing(4, arrayOf(1, 3, 5))
    )
}


/**
 * There's a staircase with N steps, and you can climb 1 or 2 steps at a time. Given N,
 * write a function that returns the number of unique ways you can climb the staircase.
 * The order of the steps matters.
 * For example, if N is 4, then there are 5 unique ways:
 * 1, 1, 1, 1
 * 2, 1, 1
 * 1, 2, 1
 * 1, 1, 2
 * 2, 2
 * What if, instead of being able to climb 1 or 2 steps at a time,
 * you could climb any number from a set of positive integers X? For example,
 * if X = {1, 3, 5}, you could climb 1, 3, or 5 steps at a time.
 * Generalize your function to take in X.
 */

fun calNumberOfPermutationUsing(numberOfStairs: Int, steps: Array<Int>): Int {

    /**
     * for me i think using hash map in this case is more suitable than using 2d array
     * because in evey step i just need the current one and previous one
     * but with 2d array i always keep unuseful info in memory even that i do not need it any more
     */
    val currentStepRow = LinkedHashMap<Int, Int?>()
    val previousStepRow = LinkedHashMap<Int, Int?>()
    //iterate over each list of number to update the number table
    steps.forEach { currentStep ->

        for (currentStairCaseStep in 1..numberOfStairs) {
            /**
             *[currentStep] represent row in table
             * [currentStairCaseStep] represent column in table
             * here i am cutting the problem into sub problems
             * i want to know if i can construct at least 1 permutation or at least one way i can climb the stairs
             * so here is my rules u can verify it if u want
             * @see row [65]
             * if(reminder of  current step of staircase(column) and the current step (row) i can climb staircase with ==0 then there is one way to climb the stairs)
             * also sum of previous value of permutation u could climb staircase with
             * also value of remaining step at step of staircase(column) multiplied by 2
             */
            if (!currentStepRow.containsKey(currentStairCaseStep)) {
                if (currentStep % currentStairCaseStep == 0) currentStepRow[currentStairCaseStep] = 1
                else currentStepRow[currentStairCaseStep] = 0

            } else {
                val remainingStep = currentStairCaseStep - currentStep
                var currentStepValue: Int = previousStepRow[currentStairCaseStep]!!
                // if equal null that means there is way to climb stair with current steps
                if (previousStepRow[remainingStep] == null) {
                    currentStepValue += 1
                }
                //if the previous value of previous step of remaining space has value >0 then we multiply the value by 2 which mean we have 2 ways to form unique ways
                else if (previousStepRow[remainingStep]!! > 0) {
                    currentStepValue += previousStepRow[remainingStep]!! * 2
                }
                if (currentStairCaseStep % currentStep == 0) {
                    currentStepValue += 1
                }
                //update the table with current step value
                currentStepRow[currentStairCaseStep] = currentStepValue
            }
        }
        //set current row as prev row to use it with the next row
        previousStepRow.putAll(currentStepRow)


    }

    return currentStepRow[numberOfStairs] ?: 0
}
