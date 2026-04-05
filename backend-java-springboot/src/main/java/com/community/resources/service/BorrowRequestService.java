@Service
public class BorrowRequestService {

    @Autowired
    BorrowRequestRepository repository;

    @Autowired
    ItemRepository itemRepository;

    public BorrowRequest requestItem(Long itemId, String requester){

        Item item = itemRepository.findById(itemId).get();

        if(item.getOwnerName().equals(requester)){
            throw new RuntimeException("Owner cannot request own item");
        }

        BorrowRequest request = new BorrowRequest();
        request.setItemId(itemId);
        request.setRequesterName(requester);
        request.setStatus("PENDING");

        item.setStatus("REQUESTED");
        itemRepository.save(item);

        return repository.save(request);
    }

}
