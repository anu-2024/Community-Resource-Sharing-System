@Entity
public class BorrowRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long itemId;

    private String requesterName;

    private String status;
    // PENDING, APPROVED, REJECTED
}
