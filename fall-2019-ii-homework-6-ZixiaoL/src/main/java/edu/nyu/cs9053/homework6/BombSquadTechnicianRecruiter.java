package edu.nyu.cs9053.homework6;

/**
 * User: blangel
 * Date: 10/11/17
 * Time: 8:03 AM
 */
public class BombSquadTechnicianRecruiter {

    //Since we only have budget for one technician and the recruit method is static, we have to use a static instace field here
    private static final BombSquadTechnician technician = new ConcreteBombSquadTechnician();

    public static BombSquadTechnician recruit() {
        return technician;
    }

}
