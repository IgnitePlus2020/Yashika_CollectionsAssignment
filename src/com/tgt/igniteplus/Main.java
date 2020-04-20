package com.tgt.igniteplus;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
public class Main {

    static List<IgniteMembers> Members = new ArrayList<>();
    static List<String> Department = new ArrayList<>();
    static Set<String> SkillSet = new HashSet<>();

    public static void main(String[] args) {
        Set<String> gauthamSkillSet = new HashSet<>();
        gauthamSkillSet.add("Java");
        gauthamSkillSet.add("SQL");
        gauthamSkillSet.add("DS");

        Set<String> divyaSkillSet = new HashSet<>();
        divyaSkillSet.add("Java");
        divyaSkillSet.add("NOSQL");
        divyaSkillSet.add("ML");

        Set<String> amitSkillSet = new HashSet<>();
        amitSkillSet.add("Linux");
        amitSkillSet.add("PSQL");
        amitSkillSet.add("Scripting");

        Set<String> naveenSkillSet = new HashSet<>();
        naveenSkillSet.add("Chef");
        naveenSkillSet.add("React");
        naveenSkillSet.add("AI");

        Department.add("Data Science");
        Department.add("Infrastructure");

        Members.add(new IgniteMembers("Gautham", "VTU", 28, Department.get(0), gauthamSkillSet));
        Members.add(new IgniteMembers("Divya", "TGT", 26, Department.get(0), divyaSkillSet));
        Members.add(new IgniteMembers("Amit", "TMT", 25, Department.get(1), amitSkillSet));
        Members.add(new IgniteMembers("Naveen", "DOJO", 22, Department.get(1), naveenSkillSet));
        int op;

        do {
            System.out.println("*****************************************\n"+
                    "Menu:\n" +
                    "1.To display all the Departments\n" +
                    "2. Create a new Department\n" +
                    "3. Delete a Department\n" +
                    "4. Display all Members as per department\n" +
                    "5. Create a Member and add to the department\n" +
                    "6. Display members based on given skill\n" +
                    "7. Swap department of a member\n" +
                    "8. Add new skill set to all members of a department\n" +
                    "******************************************************");
            Scanner in = new Scanner(System.in);
            System.out.print("Enter your option:\t");
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    displayDept();
                    break;
                case 2:
                    createDept();
                    break;
                case 3:
                    deleteDept();
                    break;
                case 4:
                    displayDeptMembers();
                    break;
                case 5:
                    createMember();
                    break;
                case 6:
                    displayMemberSkillWise();
                    break;
                case 7:
                    swapDept();
                    break;
                case 8:
                    addSkill();
                    break;
                default:
                    System.out.print("Invalid option!\n");
            }
        }
        while (true);

    }

    private static void displayDept() {
        int i = 1;
        for (String dept : Department) {
            System.out.println(i + ". " + dept);
            i++;
        }

    }

    private static void createDept() {
        System.out.println("Enter the new department to be created");
        Scanner in = new Scanner(System.in);
        String newDept = in.nextLine();
        Department.add(newDept);
    }

    private static void deleteDept() {
        Scanner in = new Scanner(System.in);
        int deptChoice;
        String deleteDept = null;
        System.out.println("\nEnter the department to be deleted:\t");
        int j = 1;
        for (String deptObj : Department) {
            System.out.println(j + ". " + deptObj);
            j++;
        }
        deptChoice = in.nextInt();
        int k = 1;
        for (String deptObj : Department) {
            if (k == deptChoice) {
                deleteDept = deptObj;
                break;
            }
            k++;
        }
        Department.remove(deleteDept);
        System.out.println("Removed!");
    }
    private static void displayDeptMembers() {
        for (String dept : Department) {
            System.out.println(dept + ":");
            for (IgniteMembers mem : Members) {
                if (dept == mem.getDepartment())
                    System.out.println(mem.getName());
            }
            System.out.println("-------------------------------------------------");
        }
    }

    private static void createMember() {
        Scanner in = new Scanner(System.in);
        Set<String> SkillSet = new HashSet<>();
        String name, college, dept = "";
        int age, n;
        System.out.println("Enter the Name of the member:");
        name = in.next();
        for (IgniteMembers mem : Members) {
            if (mem.getName().contains(name)) {
                System.out.print("Name already exists!\n" + "Enter a UNIQUE name:");
                name = in.next();
            }
        }
        System.out.println("Enter the College of the member: ");
        college = in.next();
        System.out.println("Enter the age of the member: ");
        age = in.nextInt();
        System.out.println("Enter the number of skills: ");
        n = in.nextInt();
        System.out.println("Enter the Skill Set: ");
        for (int k = 1; k <=n+1; k++) {
            String newSkill=in.nextLine();
            SkillSet.add(newSkill);
        }
        System.out.println("Add the member to one of the departments:");
        int j = 1;
        for (String dep : Department) {
            System.out.println(j + ". " + dep);
            j++;
        }
        System.out.println("Enter your option:\t");
        int deptCh = in.nextInt();
        int k = 1;
        for (String dep : Department) {
            if (k == deptCh) {
                dept = dep;
                break;
            }
            k++;
        }
        Members.add(new IgniteMembers(name, college, age, dept, SkillSet));
        System.out.println("Created!");
    }

    private static void displayMemberSkillWise() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the skill to be searched");
        String skill = in.nextLine();
        for (IgniteMembers mem : Members) {
            if (mem.getSkillSet().contains(skill))
                System.out.println(mem);
        }
    }

    private static void swapDept() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the name of the member to be swapped");
        String name = in.next();
        int j = 1;
        for (String dep : Department) {
            System.out.println(j + ". " + dep);
            j++;
        }
        System.out.println("Enter the choice of department:");
        String dept = null;
        int deptCh = in.nextInt();
        int k = 1;
        for (String dep : Department) {
            if (k == deptCh) {
                dept = dep;
                break;
            }
            k++;
        }
        for (IgniteMembers mem : Members) {
            if (mem.getName().contains(name))
                mem.setDepartment(dept);
        }
    }

    private static void addSkill() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the new skill: ");
        String newSkill = in.next();
        System.out.println("Enter the department choice: ");
        int j = 1;
        for (String dep : Department) {
            System.out.println(j + ". " + dep);
            j++;
        }
        String dept = null;
        int deptCh = in.nextInt();
        int k = 1;
        for (String dep : Department) {
            if (k == deptCh) {
                dept = dep;
                break;
            }
            k++;
        }
        for (IgniteMembers mem : Members) {
            if (mem.getDepartment().contains(dept)) {
                Set<String> skill = mem.getSkillSet();
                skill.add(newSkill);
                mem.setSkillSet(skill);
            }
        }
        System.out.println("Added!");
        for (IgniteMembers mem : Members)
            if (mem.getDepartment().contains(dept))
                System.out.println("Name: " + mem.getName() + "\tSkills: " + mem.getSkillSet());
    }

}




















