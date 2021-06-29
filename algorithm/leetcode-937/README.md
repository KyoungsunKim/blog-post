# [LeetCode] 937. Reorder Data in Log Files(로그 파일 재정렬) - 문제풀이

[https://leetcode.com/problems/reverse-string/submissions/](https://leetcode.com/problems/reverse-string/submissions/)

### 문제 분석

로그를 재정렬하는 문제다. 기준은 아래와 같다.
- 로그의 가장 앞 부분은 식별자
- 문자로 구성된 로그가 숫자로 구성된 로그보다 앞에 위치
- 식별자는 순서에 영향을 미치지 않지만, 문자가 동일할 경우 식별자 순으로 정렬
- 숫자 로그는 입력 순서대로 정렬

### 해답

모든 소스코드는 [github](https://github.com/kyoungsun/algorithm-practice)에서 다운 받을 수 있습니다.

```
public class Question937 {
    public String[] reorderLogFiles(String[] logs) {
        List<Log> digits = new ArrayList<>();
        List<Log> letters = new ArrayList<>();
        for (String str : logs) {
            Log log = new Log(str);
            if (log.isDigit()) {
                digits.add(log);
            } else {
                letters.add(log);
            }
        }

        letters.sort((o1, o2) -> {
            int compare = o1.getValues().compareTo(o2.getValues());
            if (compare == 0) {
                return o1.getIdentifier().compareTo(o2.getIdentifier());
            }
            return compare;
        });

        List<String> sorted = new ArrayList<>();
        for (Log log : letters) {
            sorted.add(log.toString());
        }
        for (Log log : digits) {
            sorted.add(log.toString());
        }

        return sorted.toArray(new String[sorted.size()]);
    }

    class Log {
        private static final String DELIMITER = " ";
        String[] values;

        public Log(String log) {
            this.values = log.split(DELIMITER);
        }

        public boolean isDigit() {
            for (int i = 1, n = values.length; i < n; i++) {
                char[] chars = values[i].toCharArray();
                for (char c : chars) {
                    if (!Character.isDigit(c)) {
                        return false;
                    }
                }
            }

            return true;
        }

        public String getIdentifier() {
            return values[0];
        }

        public String getValues() {
            StringJoiner joiner = new StringJoiner(DELIMITER);
            for (int i = 1, n = values.length; i < n; i++) {
                joiner.add(values[i]);
            }

            return joiner.toString();
        }

        public String toString() {
            StringJoiner joiner = new StringJoiner(DELIMITER);
            for (String str : values) {
                joiner.add(str);
            }

            return joiner.toString();
        }
    }

}
```

