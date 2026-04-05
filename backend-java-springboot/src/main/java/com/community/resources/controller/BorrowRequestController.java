@RestController
@RequestMapping("/requests")
public class BorrowRequestController {

    @Autowired
    BorrowRequestService service;

    @PostMapping
    public BorrowRequest requestItem(@RequestBody Map<String,String> body){

        Long itemId = Long.parseLong(body.get("itemId"));
        String requester = body.get("requester");

        return service.requestItem(itemId, requester);
    }

}
