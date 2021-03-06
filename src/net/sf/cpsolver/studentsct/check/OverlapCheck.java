package net.sf.cpsolver.studentsct.check;

import java.util.HashMap;

import net.sf.cpsolver.coursett.model.TimeLocation;
import net.sf.cpsolver.studentsct.StudentSectioningModel;
import net.sf.cpsolver.studentsct.model.Assignment;
import net.sf.cpsolver.studentsct.model.Enrollment;
import net.sf.cpsolver.studentsct.model.Request;
import net.sf.cpsolver.studentsct.model.Student;

/**
 * This class looks and reports cases when a student is enrolled into two
 * sections that are overlapping in time.
 * 
 * <br>
 * <br>
 * 
 * Usage: if (new OverlapCheck(model).check()) ...
 * 
 * <br>
 * <br>
 * 
 * @version StudentSct 1.2 (Student Sectioning)<br>
 *          Copyright (C) 2007 - 2010 Tomas Muller<br>
 *          <a href="mailto:muller@unitime.org">muller@unitime.org</a><br>
 *          <a href="http://muller.unitime.org">http://muller.unitime.org</a><br>
 * <br>
 *          This library is free software; you can redistribute it and/or modify
 *          it under the terms of the GNU Lesser General Public License as
 *          published by the Free Software Foundation; either version 3 of the
 *          License, or (at your option) any later version. <br>
 * <br>
 *          This library is distributed in the hope that it will be useful, but
 *          WITHOUT ANY WARRANTY; without even the implied warranty of
 *          MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *          Lesser General Public License for more details. <br>
 * <br>
 *          You should have received a copy of the GNU Lesser General Public
 *          License along with this library; if not see
 *          <a href='http://www.gnu.org/licenses/'>http://www.gnu.org/licenses/</a>.
 */
public class OverlapCheck {
    private static org.apache.log4j.Logger sLog = org.apache.log4j.Logger.getLogger(OverlapCheck.class);
    private StudentSectioningModel iModel;

    /**
     * Constructor
     * 
     * @param model
     *            student sectioning model
     */
    public OverlapCheck(StudentSectioningModel model) {
        iModel = model;
    }

    /** Return student sectioning model */
    public StudentSectioningModel getModel() {
        return iModel;
    }

    /**
     * Check for overlapping sections that are attended by the same student
     * 
     * @return false, if there is such a case
     */
    public boolean check() {
        sLog.info("Checking for overlaps...");
        boolean ret = true;
        for (Student student : getModel().getStudents()) {
            HashMap<TimeLocation, Assignment> times = new HashMap<TimeLocation, Assignment>();
            for (Request request : student.getRequests()) {
                Enrollment enrollment = request.getAssignment();
                if (enrollment == null)
                    continue;
                for (Assignment assignment : enrollment.getAssignments()) {
                    if (assignment.getTime() == null)
                        continue;
                    for (TimeLocation time: times.keySet()) {
                        if (time.hasIntersection(assignment.getTime())) {
                            sLog.error("Student " + student + " assignment " + assignment + " overlaps with "
                                    + times.get(time));
                            ret = false;
                        }
                    }
                    times.put(assignment.getTime(), assignment);
                }
            }
        }
        return ret;
    }

}
