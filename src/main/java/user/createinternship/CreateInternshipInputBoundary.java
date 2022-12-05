package user.createinternship;

/** An interface that is intended to be implemented by the CreateInternshipInteractor class.
 * Holds one method, createInternship, that must be implemented in all classes that implement
 * this interface.
 */
public interface CreateInternshipInputBoundary {

    /** Creates a CreateInternshipResponseDS using the input from inputDS.
     * @param inputDS a CreateInternshipInputDS request model
     * @return a CreateInternshipResponseDS response model
     */
    CreateInternshipResponseDS createInternship(CreateInternshipInputDS inputDS);

}
