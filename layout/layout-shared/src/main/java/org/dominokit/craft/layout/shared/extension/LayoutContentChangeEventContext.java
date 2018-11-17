package org.dominokit.craft.layout.shared.extension;


import org.dominokit.domino.api.shared.extension.Content;
import org.dominokit.domino.api.shared.extension.EventContext;

public interface LayoutContentChangeEventContext extends EventContext {
    Content getContent();
}
