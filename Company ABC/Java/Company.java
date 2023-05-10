import java.util.*;

class Candidate {
    private String name;
    private Date dob;
    private double hscMarks;
    private double pcmPcbAvgMarks;
    private double ugCgpa;
    private double pgCgpa;
    private int numProjects;
    private boolean isFullTime;
    private double interviewMarks;
    private String isIndian;

    public Candidate(String name, Date dob, double hscMarks, double pcmPcbAvgMarks,
                     double ugCgpa, double pgCgpa, int numProjects, boolean isFullTime,
                     double interviewMarks, String isIndian) {
        this.name = name;
        this.dob = dob;
        this.hscMarks = hscMarks;
        this.pcmPcbAvgMarks = pcmPcbAvgMarks;
        this.ugCgpa = ugCgpa;
        this.pgCgpa = pgCgpa;
        this.numProjects = numProjects;
        this.isFullTime = isFullTime;
        this.interviewMarks = interviewMarks;
        this.isIndian = isIndian;
    }

    public String getName() {
        return name;
    }

    public Date getDob() {
        return dob;
    }

    public double getHscMarks() {
        return hscMarks;
    }

    public double getPcmPcbAvgMarks() {
        return pcmPcbAvgMarks;
    }

    public double getUgCgpa() {
        return ugCgpa;
    }

    public double getPgCgpa() {
        return pgCgpa;
    }

    public int getNumProjects() {
        return numProjects;
    }

    public boolean getIsFullTime() {
        return isFullTime;
    }

    public double getInterviewMarks() {
        return interviewMarks;
    }
    
    public String getIsIndian() {
        return isIndian; 
    }

    public boolean isEligible() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dob);
        return cal.get(Calendar.YEAR) >= 1999 &&
                hscMarks >= 60 &&
                (pcmPcbAvgMarks >= 50) &&
                ugCgpa >= 8 &&
                pgCgpa >= 8 &&
                numProjects >= 2 &&
                isFullTime == true &&
                interviewMarks >= 35 &&
                isIndian.equals("Indian");
    }
}

class RecruitingTeam {
    private static double interviewMarksWeightage = 0.5;

    public static double calculateFinalMarks(Candidate candidate) {
        double finalMarks = 0;
        if (candidate.isEligible()) {
            finalMarks = candidate.getHscMarks() * 0.1 +
                    candidate.getPcmPcbAvgMarks() * 0.1 +
                    candidate.getUgCgpa() * 0.3 +
                    candidate.getPgCgpa() * 0.3 +
                    candidate.getNumProjects() * 0.05 +
                    candidate.getInterviewMarks() * interviewMarksWeightage;
        }
        return finalMarks;
    }
}

class HRTeam {
    public static void notifySelectedCandidates(List<Candidate> candidates) {
        int selectedCount = 0;
        System.out.println("Selected candidates:");
        for (Candidate candidate : candidates) {
            if (RecruitingTeam.calculateFinalMarks(candidate) >= 35) {
                System.out.println("- " + candidate.getName());
                selectedCount++;
            }
        }
        System.out.println("Total selected candidates: " + selectedCount);
    }

    public static void notifyRejectedCandidates(List<Candidate> candidates) {
        int rejectedCount = 0;
        System.out.println("Rejected candidates:");
        for (Candidate candidate : candidates) {
            if (RecruitingTeam.calculateFinalMarks(candidate) < 30) {
                System.out.println("- " + candidate.getName());
                rejectedCount++;
            }
        }
        System.out.println("Total rejected candidates: " + rejectedCount);
    }
}


public class Company {
    public static void main(String[] args) {
        // create sample candidates
        Candidate candidate1 = new Candidate(
                "Tamilarasan S",
                new GregorianCalendar(2001, Calendar.NOVEMBER, 14).getTime(),
                75,
                65,
                9.5,
                9.0,
                3,
                true,
                45,
                "Indian"
        );

        Candidate candidate2 = new Candidate(
                "Janani P",
                new GregorianCalendar(2002, Calendar.JULY, 23).getTime(),
                80,
                55,
                8.0,
                8.0,
                2,
                true,
                40,
                "Indian"
        );

        Candidate candidate3 = new Candidate(
                "Sreevarshini S",
                new GregorianCalendar(2001, Calendar.DECEMBER, 02).getTime(),
                65,
                60,
                8.5,
                9.5,
                1,
                true,
                50,
                "Indian"
        );

        Candidate candidate4 = new Candidate(
                "Sneha T",
                new GregorianCalendar(2000, Calendar.AUGUST, 13).getTime(),
                70,
                40,
                9.0,
                8.5,
                2,
                true,
                40,
                "Indian"
        );

        // create list of candidates
        List<Candidate> candidates = Arrays.asList(candidate1, candidate2, candidate3, candidate4);

        // notify selected and rejected candidates
        HRTeam.notifySelectedCandidates(candidates);
        HRTeam.notifyRejectedCandidates(candidates);
    }
}

