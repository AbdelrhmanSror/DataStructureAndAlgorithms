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
        calNumberOfUniqueWays(4, arrayOf(1, 3, 5))
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

fun calNumberOfUniqueWays(numberOfStairs: Int, steps: Array<Int>): Int {
    val currentStep = HashMap<Int, Int>()
    //base case
    currentStep[0] = 1
    if (numberOfStairs == 0) return 1
    //iterate over each step and calculate the max number of unique ways i can climb this step with
    for (currentStairCaseStep in 1..numberOfStairs) {
        var maxStep = 0
        steps.forEach { step ->
            if (currentStairCaseStep - step > -1) {
                maxStep += currentStep[currentStairCaseStep - step]!!
            }
        }
        //this means we are telling that this step has max number of n ways
        currentStep[currentStairCaseStep] = maxStep
    }
    return currentStep[numberOfStairs]!!

}
