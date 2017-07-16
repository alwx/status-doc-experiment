status.command({
    name: "hello",
    title: "HelloBot",
    description: "Helps you say hello",
    color: "#CCCCCC",
    preview: function (params) {
        var text = status.components.text(
            {
                style: {
                    marginTop: 5,
                    marginHorizontal: 0,
                    fontSize: 14,
                    fontFamily: "font",
                    color: "black"
                }
            }, "Hello from the other side!");
        return {markup: status.components.view({}, [text])};
    }
});