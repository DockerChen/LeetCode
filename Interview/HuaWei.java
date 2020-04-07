import java.util.*;

public class HuaWei {
    String searchHead;

    public void lookingForDependencies() {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> map = new HashMap<>();
        //输入处理
        while (true) {
            String[] str = scanner.nextLine().split(":");
            if (str[0].equals("search head file")) {
                searchHead = str[1];
                break;
            }
            String[] dependHeads = str[1].split(" ");
            for (String dependHead : dependHeads) {
                List<String> list = new ArrayList<>();
                if (map.containsKey(str[0])) {
                    list = map.get(str[0]);

                }
                list.add(dependHead);
                map.put(str[0], list);
            }

        }
//        System.out.println(map);
//        System.out.println(searchHead);
        List<List<String>> res = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        tmp.add(searchHead);
        String cur = searchHead;
        int flag = 0;
        helper(flag, cur, map, tmp, res);
        if (res.size() == 0) {
            System.out.println("none loop include " + searchHead);
        } else {
            System.out.println("Bad coding -- loop include as bellow:");
            for (int i = 0; i < res.size(); i++) {
                for (int j = 0; j < res.get(i).size(); j++) {
                    System.out.print(res.get(i).get(j) + " ");
                }
                System.out.println();
            }
        }

    }

    public void helper(int flag, String nextHead, Map<String, List<String>> map, List<String> tmp, List<List<String>> res) {
        if (flag == 1 && searchHead.equals(nextHead)) {
//            System.out.println(tmp);
            String str = tmp.get(tmp.size() - 1);
            tmp.remove(tmp.size() - 1);
            res.add(new ArrayList<>(tmp));
            tmp.add(str);
            return;

        }

        if (map.containsKey(nextHead)) {
            flag = 1;
            List<String> nextHeads = map.get(nextHead);
            for (int i = 0; i < nextHeads.size(); i++) {
                nextHead = nextHeads.get(i);
                tmp.add(nextHead);

                helper(flag, nextHead, map, tmp, res);
                tmp.remove(tmp.size() - 1);
            }

        }

    }

    public void sortString() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String str = scanner.next();
            if (!map.containsKey(str)) {
                list.add(str);
            }
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            System.out.println(str + " " + map.get(str));
        }

    }

    public void judgeCommand() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String str = scanner.nextLine();
            if (str.equals("")) {
                break;
            }
            System.out.println(judge(str));
        }
    }

    private int judge(String str) {
        String[] strs = str.split(" ");
        if (strs.length < 2) {
            return 0;
        }
        if (strs[0].equals("AND") || strs[0].equals("OR")) {
            return 0;
        }

        for (int i = 0; i < strs.length - 1; i++) {
            if (strs[i].equals("AND") || strs[i].equals("OR") || strs[i].equals("NOT")) {
                if (strs[i + 1].compareTo("a") >= 0 && strs[i + 1].compareTo("z") <= 0) {
                    continue;
                }
                return 0;

            }
            if (strs[i].compareTo("a") >= 0 && strs[i].compareTo("z") <= 0) {
                if (strs[i + 1].equals("AND") || strs[i + 1].equals("OR") || strs[i + 1].equals("NOT")) {
                    continue;
                }
                return 0;

            }

            if (!strs[i].equals("AND") && !strs[i].equals("OR") && !strs[i].equals("NOT") &&
                    (strs[i].compareTo("a") < 0 || strs[i].compareTo("z") > 0)) {
                return 0;
            }

        }

        if (strs[strs.length - 1].compareTo("a") < 0 || strs[strs.length - 1].compareTo("z") > 0) {
            return 0;
        }

        return 1;
    }

    public static void main(String[] args) {
        HuaWei huaWei = new HuaWei();
        /*
        a.h:b.h c.h
        b.h:c.h a.h
        c.h:a.h
        search head file:a.h
        * */
//        main.lookingForDependencies();
        /*
        5
        39ZWH69943
        70TPT88092
        26RAU81365
        39ZWH89940
        26RAU81365
        * */
//        main.sortString();
        huaWei.judgeCommand();

    }
}
