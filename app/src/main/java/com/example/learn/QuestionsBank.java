package com.example.learn;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {

    private static List<QuestionsList> additionQuestions(){

        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("1+1", "4","5","2","2","");
        final QuestionsList question2 = new QuestionsList("2+2", "4", "5", "2", "4", "");
        final QuestionsList question3 = new QuestionsList("2+3", "4", "5", "2", "5", "");
        final QuestionsList question4 = new QuestionsList("3+3", "5", "6", "7", "6", "");
        final QuestionsList question5 = new QuestionsList("3+1", "4", "5", "2", "4", "");
        final QuestionsList question6 = new QuestionsList("3+4", "5", "6", "7", "7", "");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);

        return questionsLists;
    }

    private static List<QuestionsList> multiplicationQuestions(){

        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("1x1", "1", "2", "3", "1", "");
        final QuestionsList question2 = new QuestionsList("2x2", "4", "5", "6", "4", "");
        final QuestionsList question3 = new QuestionsList("2x3", "4", "5", "6", "6", "");
        final QuestionsList question4 = new QuestionsList("3x3", "7", "8", "9", "9", "");
        final QuestionsList question5 = new QuestionsList("3x1", "1", "2", "3", "3", "");
        final QuestionsList question6 = new QuestionsList("2x4", "7", "8", "9", "8", "");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);

        return questionsLists;
    }

    private static List<QuestionsList> divisionQuestions(){

        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("1÷1", "0", "1", "2", "1", "");
        final QuestionsList question2 = new QuestionsList("2÷2", "0", "1", "2", "1", "");
        final QuestionsList question3 = new QuestionsList("10÷2", "4", "5", "2", "5", "");
        final QuestionsList question4 = new QuestionsList("15÷3", "4", "5", "2", "5", "");
        final QuestionsList question5 = new QuestionsList("30÷5", "4", "5", "6", "6", "");
        final QuestionsList question6 = new QuestionsList("50÷10", "5", "6", "7", "5", "");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);

        return questionsLists;
    }

    private static List<QuestionsList> subtractionQuestions(){

        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("1-1", "0", "1", "2", "0", "");
        final QuestionsList question2 = new QuestionsList("2-1", "0", "1", "2", "1", "");
        final QuestionsList question3 = new QuestionsList("3-1", "0", "1", "2", "2", "");
        final QuestionsList question4 = new QuestionsList("10-3", "5", "6", "7", "7", "");
        final QuestionsList question5 = new QuestionsList("5-1", "4", "5", "2", "4", "");
        final QuestionsList question6 = new QuestionsList("10-4", "5", "6", "7", "6", "");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);

        return questionsLists;
    }

    public static List<QuestionsList> getQuestions(String selectedTopicName) {
        switch (selectedTopicName){
            case "addition":
                return additionQuestions();
            case "multiplication":
                return multiplicationQuestions();
            case "division":
                return divisionQuestions();
            default:
                return subtractionQuestions();
        }
    }
}
