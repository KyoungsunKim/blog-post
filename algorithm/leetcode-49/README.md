# [LeetCode] 49. Group Anagrams(그룹 애너그램) - 문제풀이

[https://leetcode.com/problems/group-anagrams/](https://leetcode.com/problems/group-anagrams/)

### 문제 분석

같은 문자로 이루어져 있는 단어들을 찾아서 그룹화 하는 문제다.
- 애너그램은 단어나 구문을 재배열한 글자나 구문을 뜻함
- 원래 사용되는 단어는 1번만 존재
- 결과의 순서는 중요하지 않음

### 해답

모든 소스코드는 [github](https://github.com/kyoungsun/algorithm-practice)에서 다운 받을 수 있습니다.

```
public class Question49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List> groupAnagrams = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            if (!groupAnagrams.containsKey(key)) {
                groupAnagrams.put(key, new ArrayList<>());
            }
            groupAnagrams.get(key).add(str);
        }

        return new ArrayList(groupAnagrams.values());
    }
}
```

