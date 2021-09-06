package org.javajarvis.SistemCuti_UASJava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailPayload {

    String to;
    String subject;
    String text;
}
