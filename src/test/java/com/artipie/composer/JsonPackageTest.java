/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 artipie.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.artipie.composer;

import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;
import java.io.IOException;
import org.cactoos.io.ResourceOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link JsonPackage}.
 *
 * @since 0.1
 */
class JsonPackageTest {

    /**
     * Example package read from 'minimal-package.json'.
     */
    private Package pack;

    @BeforeEach
    void init() throws Exception {
        final ResourceOf json = new ResourceOf("minimal-package.json");
        this.pack = new JsonPackage(ByteSource.wrap(ByteStreams.toByteArray(json.stream())));
    }

    @Test
    void shouldExtractName() throws IOException {
        MatcherAssert.assertThat(
            this.pack.name().key().string(),
            Matchers.is("vendor/package.json")
        );
    }

    @Test
    void shouldExtractVersion() throws IOException {
        MatcherAssert.assertThat(
            this.pack.version(),
            Matchers.is("1.2.0")
        );
    }
}
