package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import model.Contact;
import model.Group;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static common.CommonFunction.randomString;

public class Generator {

    @Parameter(names = {"--type", "- t"})
    String type;

    @Parameter(names = {"--output", "- o"})
    String output;

    @Parameter(names = {"--format", "- f"})
    String format;

    @Parameter(names = {"--count", "- c"})
    Integer count;

    public static void main(String[] args) throws IOException {

        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных " + type);
        }
    }

    private Object generateGroups() {
        return generateData(() -> new Group()
                .withName(randomString(10))
                .withHeader(randomString(10))
                .withFooter(randomString(10)));
    }

    private Object generateContacts() { //Метод генерации без использования саплаеров
        var result = new ArrayList<Contact>();
        for (int i = 0; i < count; i++) {
            result.add(new Contact()
                    .withLastName(randomString(i * 10))
                    .withFirstName(randomString(i * 10))
                    .withAddress(randomString(i * 10))
                    .withEmail(randomString(i * 10)));
        }
        return result;
    }

    private Object generateData(Supplier<Object> dataSupplier) {
        return Stream.generate(dataSupplier).limit(count).collect(Collectors.toList());
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            var json = mapper.writeValueAsString(data);

            try (var writer = new FileWriter(output)) {
                writer.write(json);
            }
        } else if ("yaml".equals(format)) {
            ObjectMapper mapper = new YAMLMapper();
            mapper.writeValue(new File(output), data);
        } else if ("xml".equals(format)) {
            XmlMapper mapper = new XmlMapper();
            mapper.writeValue(new File(output), data);
        } else {
            throw new IllegalArgumentException("Неизвестный формат данных " + format);
        }
    }
}
