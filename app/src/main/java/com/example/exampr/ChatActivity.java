package com.example.exampr;

public class ChatActivity {
    private RecyclerView recyclerView;
    private MessageAdapter messageAdapter;
    private FirebaseDatabase database;
    private List<Message> messageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = findViewById(R.id.recyclerView);
        messageAdapter = new MessageAdapter(messageList);
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        DatabaseReference chatRef = database.getReference("chats");

        // Обработка сообщений
        findViewById(R.id.sendButton).setOnClickListener(v -> sendMessage());

        chatRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Message newMessage = dataSnapshot.getValue(Message.class);
                messageList.add(newMessage);
                messageAdapter.notifyDataSetChanged();
            }
            // Другие методы ChildEventListener можно оставить пустыми
        });
    }

    private void sendMessage() {
        TextInputEditText messageEditText = findViewById(R.id.messageEditText);
        String messageText = messageEditText.getText().toString();

        if (!messageText.isEmpty()) {
            DatabaseReference chatRef = database.getReference("chats");
            Message message = new Message(FirebaseAuth.getInstance().getCurrentUser().getEmail(), messageText);
            chatRef.push().setValue(message);
            messageEditText.setText("");
        }
    }
}
//chat