package com.interviewprep.util;

public class AppUtils {
    
    public static double calculatePercentage(int correctAnswers, int totalQuestions) {
        if (totalQuestions == 0) {
            return 0;
        }
        return (double) correctAnswers / totalQuestions * 100;
    }

    public static int calculateScore(int correctAnswers, int totalQuestions) {
        return (int) calculatePercentage(correctAnswers, totalQuestions);
    }

    public static String getGrade(double percentage) {
        if (percentage >= 90) return "A";
        if (percentage >= 80) return "B";
        if (percentage >= 70) return "C";
        if (percentage >= 60) return "D";
        return "F";
    }
}
