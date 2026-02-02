package practiceExample;

class String_class{
    static void main(String[] args) {
        String s1 = "hello";
        System.out.println(s1.hashCode());
        s1 = s1 + "world";
        System.out.println(s1.hashCode());

//        System.out.println(s1.equals(s2));
//        System.out.println(s1 == s2);
    }
}