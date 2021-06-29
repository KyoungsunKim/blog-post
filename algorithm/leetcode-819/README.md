# [LeetCode] 819. Most Common Word(가장 흔한 단어) - 문제풀이

[https://leetcode.com/problems/most-common-word/](https://leetcode.com/problems/most-common-word/)

### 문제 분석

가장 많이 사용된 단어를 찾는 문제다. 기준은 아래와 같다.
- 모든 문자는 소문자로 취급함으로 결과도 소문자로 출력할 것
- 금지어로 설정되지 않은 단어 중에 가장 많이 사용된 단어를 찾을 것
- 모든 구두점은 무시됨

### 해답

모든 소스코드는 [github](https://github.com/kyoungsun/algorithm-practice)에서 다운 받을 수 있습니다.

```
public class Question819 {
    private final static String DELIMITER = " ";

    public String mostCommonWord(String paragraph, String[] banned) {
        List<String> words = new ArrayList<>(
                Arrays.asList(
                        paragraph.toLowerCase()
                        .replaceAll("[^a-zA-Z0-9]+", " ")
                        .split(DELIMITER)));
        words.removeAll(Arrays.asList(banned));

        String mostCommonWord = words.get(0);
        int maxCount = 0;

        for (String first : words) {
            int count = 0;
            for (String second : words) {
                if (Objects.equals(first, second)) {
                    count++;
                }
            }

            if (count > maxCount) {
                maxCount = count;
                mostCommonWord = first;
            }
        }

        return mostCommonWord;
    }

}
```
