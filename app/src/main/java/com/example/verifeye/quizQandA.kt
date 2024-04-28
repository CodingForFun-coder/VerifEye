package com.example.verifeye

object QuestionAnswer {
    var question = arrayOf(
        "What is the first step in challenging biased news coverage?",
        "Why is it important to be aware of the political perspective of the sources used in a news story?",
        "What is one way to assess the diversity of a news outlet?",
        "Whose point of view is often prioritized in political and economic news coverage?",
        "What example does the article give of media applying different standards to different groups?",
        "How does the coverage of drug crises typically demonstrate bias?",
        "What is often implied in the coverage of women on welfare regarding their personal choices?",
        "How does the use of the term \"racial preference\" influence public perception?",
        "What critical aspect does the coverage of \"reverse discrimination\" usually miss?",
        "How can the placement of a story in a newspaper or on a broadcast affect public opinion?"
    )
    var choices = arrayOf(
        arrayOf("Ignoring the bias", "Documenting the bias", "Changing the channel", "Writing a complaint"),
        arrayOf("It determines the broadcast time", "It influences the story’s credibility", "It affects the length of the story", "It changes the journalistic style"),
        arrayOf("Comparing the diversity at the outlet to the community it serves", "Counting the number of stories they produce", "Evaluating the font size used in articles", "Checking the type of news covered"),
        arrayOf("Politicians and corporate executives", "Local communities", "International observers", "Grassroots activists"),
        arrayOf("Equal treatment of all groups", "Referring to young criminals of color as \"superpredators\"", "Identifying all think tanks by their funding sources", "Consistent terminology in all reports"),
        arrayOf("By focusing only on celebrities", "By focusing primarily on African Americans", "By interviewing only experts", "By using scientific data"),
        arrayOf("Their educational qualifications", "Their entrepreneurial spirit", "Their sexual \"promiscuity\"", "Their professional background"),
        arrayOf("It has no impact", "It is seen more favorably", "It negatively impacts the perception of affirmative action programs", "It encourages neutrality"),
        arrayOf("Quoting diverse sources", "Mentioning institutional factors that empower prejudice", "Presenting statistical analysis", "Showcasing multiple perspectives"),
        arrayOf("It has minimal influence", "It influences how prominently the story is read or viewed", "It affects the weather forecast", "It determines the paper quality")
    )
    var correctAnswers = arrayOf(
        "Documenting the bias",
        "It influences the story’s credibility",
        "Comparing the diversity at the outlet to the community it serves",
        "Politicians and corporate executives",
        "Referring to young criminals of color as \"superpredators\"",
        "By focusing primarily on African Americans",
        "Their sexual \"promiscuity\"",
        "It negatively impacts the perception of affirmative action programs",
        "Mentioning institutional factors that empower prejudice",
        "It influences how prominently the story is read or viewed"
    )
}